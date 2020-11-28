package org.tsystems.tschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tsystems.tschool.dto.CatalogDto;
import org.tsystems.tschool.service.ArticleService;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    private final ArticleService articleService;

    public CatalogController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping()
    public String getCatalog(Model model) {
        CatalogDto catalog;
        catalog = articleService.getCatalog();
        model.addAttribute("catalog", catalog);
        return "user/catalog";
    }
}
