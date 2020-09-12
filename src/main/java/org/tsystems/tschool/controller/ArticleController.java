package org.tsystems.tschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.service.ArticleService;

import javax.servlet.http.HttpSession;
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
        List<Article> articleList = new ArrayList<>();
//        articleList.add(new Article("title1",1,"category1","weight","black",10));
//        articleList.add(new Article("title2",1,"category2","weight","black",15));
//        articleList.add(new Article("title3",1,"category3","weight","black",1));

        model.addAttribute("articles", articleList);
        return "index";
    }

    @GetMapping("/articles")
    public String getAllArticlesPage(Model model){
                model.addAttribute("articlesList",articleService.findAll());
                return "employee/articlesListPage";
    }
}
