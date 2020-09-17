package org.tsystems.tschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.service.ArticleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("")
    public String getAllArticlesPage(Model model){
                model.addAttribute("articles",articleService.findAll());
                return "articles/articlesListPage";
    }

    @GetMapping("/add-article-page")
    public String getAddArticlePage(Article article){
        return "articles/add-article-page";
    }

    @PostMapping("/add")
    public String addArticle( @ModelAttribute("article") @Valid Article article, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "articles/add-article-page";
        }
        articleService.saveArticle(article);
        return "redirect:/articles";
    }

    @GetMapping("/edit/{id}")
    public String editArticle(@PathVariable Long id, Model model){
        model.addAttribute(articleService.findById(id));
        return "articles/edit-article-page";
    }

}
