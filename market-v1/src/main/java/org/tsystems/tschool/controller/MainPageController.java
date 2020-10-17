package org.tsystems.tschool.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.tsystems.tschool.dto.CartDto;
import org.tsystems.tschool.service.CartService;

import javax.servlet.http.HttpSession;

@Controller
public class MainPageController {

    private final CartService cartService;

    public MainPageController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping()
    public String getIndexPage(Authentication authentication, HttpSession session) {

        if (authentication != null) {
            CartDto cartSessionDto = (CartDto) session.getAttribute("cart");

            if (cartSessionDto != null) {
                CartDto cartDto = cartService.findByUsername(authentication.getName());

                if (cartDto.getCartItems().isEmpty()) {
                    cartService.addItemsToDatabase(cartSessionDto, authentication.getName());
                } else {
                    cartService.clearSessionCart(cartSessionDto);
                }
                session.setAttribute("cart", null);
            }
        }
        return "index";
    }
}
