package org.tsystems.tschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tsystems.tschool.entity.Article;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/", "/product"})
public class ArticleController {

    @GetMapping()
    public String getIndexPage(Model model, HttpSession session){
        List<Article> articleList = new ArrayList<>();
//        articleList.add(new Article("title1",1,"category1","weight","black",10));
//        articleList.add(new Article("title2",1,"category2","weight","black",15));
//        articleList.add(new Article("title3",1,"category3","weight","black",1));
        model.addAttribute("articles", articleList);
        return "index";
    }
}
