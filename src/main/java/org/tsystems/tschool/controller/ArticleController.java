package org.tsystems.tschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.service.ArticleService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping()
    public String getIndexPage(Model model, HttpSession session) {
        return "index";
    }

    @GetMapping("/articles")
    public String getAllArticlesPage(Model model){
                model.addAttribute("articles",articleService.findAll());
                return "employee/articlesListPage";
    }

    @GetMapping("/articles/add-article-page")
    public String getAddArticlePage(Article article){
        return "employee/add-article-page";
    }

    @PostMapping("/articles/add")
    public String addArticle( @ModelAttribute("article") @Valid Article article, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "employee/add-article-page";
        }
        articleService.saveArticle(article);
        return "redirect:/articles";
    }

    @GetMapping("/articles/edit/{id}")
    public String editArticle(@PathVariable Long id, Model model){
        model.addAttribute(articleService.findById(id));
        return "employee/edit-article-page";
    }



//    @RequestMapping("/articles/delete/{id}")
//    public String deleteArticle(@PathVariable String id){
//        articleService.removeArticleById(Long.parseLong(id));
//        return "redirect:/articles";
//    }
}
