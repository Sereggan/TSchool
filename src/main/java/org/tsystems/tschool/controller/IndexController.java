package org.tsystems.tschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tsystems.tschool.dto.CatalogDto;
import org.tsystems.tschool.service.ArticleService;

@Controller
public class IndexController {

    private final ArticleService articleService;

    public IndexController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping()
    public String getIndexPage() {
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
