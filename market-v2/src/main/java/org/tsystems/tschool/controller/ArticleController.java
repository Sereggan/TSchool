package org.tsystems.tschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.service.ArticleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;
    private static final String REDIRECT = "redirect:";
    private static final String ARTICLES_URL = "/articles";


    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping()
    public String getAllArticlesPage(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return ARTICLES_URL + "/articlesListPage";
    }

    @GetMapping("/add-article-page")
    public String getAddArticlePage(ArticleDto articleDto) {
        return ARTICLES_URL + "/add-article-page";
    }

    @PostMapping("/add")
    public String addArticle(@ModelAttribute("articleDto") @Valid ArticleDto articleDto, BindingResult result) {
        if (result.hasErrors()) {
            return ARTICLES_URL + "/add-article-page";
        }

        articleService.saveArticle(articleDto);
        return REDIRECT + ARTICLES_URL;
    }

    @GetMapping("/edit/article-info-page/{id}")
    public String getInfoArticlePage(@PathVariable Long id, Model model) {
        ArticleDto articleDto = articleService.findById(id);
        model.addAttribute("articleDto", articleDto);
        return "articles/article-edit-page";
    }

    @PostMapping("/edit/{id}")
    public String editArticle(@PathVariable Long id, @ModelAttribute("articleDto") ArticleDto articleDto,
                              BindingResult result) {
        if (result.hasErrors()) {
            return ARTICLES_URL + "/article-edit-page";
        }
        articleService.updateArticle(articleDto);
        return REDIRECT + ARTICLES_URL;
    }

    @GetMapping("/values/{id}")
    public String getValuesPage(@PathVariable Long id, Model model) {
        model.addAttribute("articleCategoriesDto", articleService.getAllCategoriesAndValuesByArticleId(id));
        return "articles/article-values-page";
    }

    @GetMapping("/values/{id}/add/{valueId}")
    public String addCategory(@PathVariable(name = "id") Long articleId, @PathVariable(name = "valueId") Long valueID) {
        articleService.addValue(articleId, valueID);

        return REDIRECT + "/articles/values/{id}";
    }

    @GetMapping("/values/{id}/delete/{valueId}")
    public String deleteCategory(@PathVariable(name = "id") Long articleId,
                                 @PathVariable(name = "valueId") Long valueID) {
        articleService.deleteValue(articleId, valueID);

        return REDIRECT + "/articles/values/{id}";
    }

    @GetMapping("/delete/{id}")
    public String deleteArticleById(@PathVariable Long id) {
        articleService.removeArticleById(id);
        return REDIRECT + ARTICLES_URL;
    }

    @GetMapping("/rating")
    public String getTopArticles(Model model) {
        model.addAttribute("articles", articleService.getArticlesRating());
        return ARTICLES_URL + "/top-articles";
    }
}
