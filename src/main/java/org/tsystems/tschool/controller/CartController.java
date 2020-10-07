package org.tsystems.tschool.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.dto.CartDto;
import org.tsystems.tschool.dto.CartItemDto;
import org.tsystems.tschool.mapper.ArticleDtoMapper;
import org.tsystems.tschool.mapper.CartDtoMapper;
import org.tsystems.tschool.service.ArticleService;
import org.tsystems.tschool.service.CartService;

import javax.servlet.http.HttpSession;
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

    private final CartDtoMapper mapper
            = Mappers.getMapper(CartDtoMapper.class);


    @GetMapping("/buy/{id}")
    public String buy(@PathVariable Long id,  Authentication authentication, HttpSession session){
        Optional<ArticleDto> articleDto = articleService.findById(id);
        if(!articleDto.isPresent()){
            return "redirect:/catalog";
        }
        CartDto cart;
        if(authentication==null){
            cart = createCartIfDoesntExist(session);
            cart = cartService.addArticleInSession(cart, articleDto.get());
            session.setAttribute("cart",cart);
        } else {
            cart = cartService.findByUsername(authentication.getName());
            cartService.addArticle(cart, articleDto.get());
        }
        return "redirect:/catalog";
    }

    private CartDto createCartIfDoesntExist(HttpSession httpSession) {
        if (httpSession.getAttribute("cart") == null) {
            CartDto cart = new CartDto();
            httpSession.setAttribute("cart", cart);
            return cart;
        }
        return (CartDto) httpSession.getAttribute("cart");
    }
}
