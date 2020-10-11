package org.tsystems.tschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tsystems.tschool.dto.CartDto;
import org.tsystems.tschool.dto.CatalogDto;
import org.tsystems.tschool.service.ArticleService;
import org.tsystems.tschool.service.CartService;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    private final ArticleService articleService;

    private final CartService cartService;


    public IndexController(ArticleService articleService, CartService cartService) {
        this.articleService = articleService;
        this.cartService = cartService;
    }

    @GetMapping()
    public String getIndexPage(Authentication authentication, HttpSession session){
        CartDto cartDto;

        if(authentication!=null) {
            cartDto = cartService.findByUsername(authentication.getName());

            if (!cartDto.getCartItems().isEmpty()) return "index";
            if (session.getAttribute("cart") == null) {
                return "index";
            }
            cartDto = (CartDto) session.getAttribute("cart");
            cartService.addItemsToDatabase(cartDto, authentication.getName());
            session.setAttribute("cart", null);
        }
        return "index";
    }

    @GetMapping("/catalog")
    public String getCatalog(Model model){
        CatalogDto catalog;
        catalog = articleService.getCatalog();
        model.addAttribute("catalog", catalog);
        return "user/catalog";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return "access-denied";
    }
}
