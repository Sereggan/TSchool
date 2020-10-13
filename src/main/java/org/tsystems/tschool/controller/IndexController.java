package org.tsystems.tschool.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tsystems.tschool.dto.CartDto;
import org.tsystems.tschool.dto.CatalogDto;
import org.tsystems.tschool.service.ArticleService;
import org.tsystems.tschool.service.CartService;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    private final CartService cartService;

    public IndexController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping()
    public String getIndexPage(Authentication authentication, HttpSession session) {
        CartDto cartDto;

        if (authentication != null) {
            cartDto = cartService.findByUsername(authentication.getName());

            if (!cartDto.getCartItems().isEmpty()) {
                cartDto = (CartDto) session.getAttribute("cart");
              //  if (!cartDto.getCartItems().isEmpty()) cartService.clearSessionCart(cartDto);

                return "index";
            }
            if (session.getAttribute("cart") == null) {
                return "index";
            }
            cartDto = (CartDto) session.getAttribute("cart");

            cartService.addItemsToDatabase(cartDto, authentication.getName());
            session.setAttribute("cart", null);
        }
        return "index";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
