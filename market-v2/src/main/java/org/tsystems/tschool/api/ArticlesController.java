package org.tsystems.tschool.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.service.ArticleService;

import java.util.List;

/**
 * Created by Sergey Nikolaychuk on 02.11.2020
 */
@RestController()
@RequestMapping("/api")
public class ArticlesController {

    private ArticleService articleService;

    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/getTopArticles")
    public List<ArticleDto> getTopArticles() {
        return articleService.getTopArticles();
    }
}
