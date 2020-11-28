package org.tsystems.tschool.api;

import org.springframework.web.bind.annotation.*;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.service.ArticleService;

import java.util.List;

/**
 * Created by Sergey Nikolaychuk on 02.11.2020
 */
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:4680"})
@RestController()
@RequestMapping("/api/articles")
public class ArticlesController {

    private ArticleService articleService;

    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/topArticles")
    public List<ArticleDto> getTopArticles() {
        return articleService.getTopArticles();
    }

    @GetMapping
    public List<ArticleDto> getAllArticlesWithParams(@RequestParam(defaultValue = "1") Float minPrice,
                                                     @RequestParam(defaultValue = "1000000") Float maxPrice,
                                                     @RequestParam(defaultValue = "1") Integer minQuantity,
                                                     @RequestParam(defaultValue = "1000000") Integer maxQuantity,
                                                     @RequestParam(defaultValue = "") String title) {
        return articleService.findAllFiltered(title,minPrice,maxPrice,minQuantity,maxQuantity);
    }
}
