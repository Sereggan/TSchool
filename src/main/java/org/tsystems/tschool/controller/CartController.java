package org.tsystems.tschool.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.dto.CartDto;
import org.tsystems.tschool.dto.CartItemDto;
import org.tsystems.tschool.dto.OrderDetailsDto;
import org.tsystems.tschool.mapper.ArticleDtoMapper;
import org.tsystems.tschool.mapper.CartDtoMapper;
import org.tsystems.tschool.service.ArticleService;
import org.tsystems.tschool.service.CartService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CartService cartService;

    private final String REDIRECT_CATALOG_URL = "redirect:catalog";

    private final CartDtoMapper mapper
            = Mappers.getMapper(CartDtoMapper.class);

    @GetMapping()
    public String getCart(Model model, Authentication authentication, HttpSession session){
        CartDto cart;
        if(authentication==null){
            cart = createCartIfDoesntExist(session);
        }else  cart = cartService.findByUsername(authentication.getName());
        model.addAttribute("cart", cart);
        return "user/cart";
    }


    @GetMapping("/buy/{id}")
    public String buy(@PathVariable Long id,  Authentication authentication, HttpSession session){
        Optional<ArticleDto> articleDto = articleService.findById(id);
        if(!articleDto.isPresent()){
            return REDIRECT_CATALOG_URL;
        }

        CartDto cart;
        if(authentication==null){
            cart = createCartIfDoesntExist(session);
            cart = cartService.addArticleInSession(cart, articleDto.get());
            session.setAttribute("cart",cart);
        } else {
            cart = cartService.findByUsername(authentication.getName());
            cartService.addArticle(cart.getId(), articleDto.get().getId());
        }
        return REDIRECT_CATALOG_URL;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,  Authentication authentication, HttpSession session){
        CartDto cart;
        if(authentication==null){
            cart = (CartDto) session.getAttribute("cart");
            cart = cartService.removeArticleInSession(cart, id);
            session.setAttribute("cart",cart);
        } else {
            cart = cartService.findByUsername(authentication.getName());
            cartService.removeArticle(cart.getId(), id);
        }
        return "redirect:/cart";
    }

    public CartDto createCartIfDoesntExist(HttpSession httpSession) {
        if (httpSession.getAttribute("cart") == null) {
            CartDto cart = new CartDto();
            httpSession.setAttribute("cart", cart);
            return cart;
        }
        return (CartDto) httpSession.getAttribute("cart");
    }

    @GetMapping("/details")
    public String getOrderDetailsPage(Model model){
        model.addAttribute("order", new OrderDetailsDto());
        return "user/confirm-order-page";
    }

    @PostMapping("/order")
    public String makeOrder(Authentication authentication, @ModelAttribute("order") @Valid OrderDetailsDto dto, BindingResult result) {

       CartDto cart = cartService.findByUsername(authentication.getName());
       if(cart.getCartItems().isEmpty()){
           return REDIRECT_CATALOG_URL;
       }
        cartService.createOrder(cart, dto);
       return "redirect:/user/orders";
    }
}
