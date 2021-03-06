package org.tsystems.tschool.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tsystems.tschool.dto.CartDto;
import org.tsystems.tschool.dto.OrderDetailsDto;
import org.tsystems.tschool.service.ArticleService;
import org.tsystems.tschool.service.CartService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/cart")
public class CartController {

    final ArticleService articleService;

    final CartService cartService;

    private static final String REDIRECT_CATALOG_URL = "redirect:/catalog";

    public CartController(ArticleService articleService, CartService cartService) {
        this.articleService = articleService;
        this.cartService = cartService;
    }

    @GetMapping()
    public String getCart(Model model, Authentication authentication, HttpSession session) {
        CartDto cart;
        if (authentication == null) {
            cart = createCartIfDoesntExist(session);
        } else cart = cartService.findByUsername(authentication.getName());
        model.addAttribute("cart", cart);
        return "user/cart";
    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable Long id, Authentication authentication, HttpSession session) {
        CartDto cart;
        if (authentication == null) {
            cart = createCartIfDoesntExist(session);
            cart = cartService.addArticleInSession(cart, id);
            session.setAttribute("cart", cart);
        } else {
            cart = cartService.findByUsername(authentication.getName());
            cartService.addArticle(cart.getId(), id);
        }
        return REDIRECT_CATALOG_URL;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Authentication authentication, HttpSession session) {
        CartDto cart;
        if (authentication == null) {
            cart = (CartDto) session.getAttribute("cart");
            cart = cartService.removeArticleInSession(cart, id);
            session.setAttribute("cart", cart);
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
    public String getOrderDetailsPage(Model model) {
        model.addAttribute("order", new OrderDetailsDto());
        return "user/confirm-order-page";
    }

    @PostMapping("/order")
    public String makeOrder(Authentication authentication, @ModelAttribute("order") @Valid OrderDetailsDto dto) {
        CartDto cart = cartService.findByUsername(authentication.getName());
        if (cart.getCartItems().isEmpty()) {
            return REDIRECT_CATALOG_URL;
        }
        cartService.createOrder(cart, dto);
        return "redirect:/user/orders";
    }
}
