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
    private String redirect = "redirect:";

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("")
    public String getAllArticlesPage(Model model){
        model.addAttribute("articles",articleService.findAll());
        return "articles/articlesListPage";
    }

    @GetMapping("/add-article-page")
    public String getAddArticlePage(ArticleDto articleDto){
        return "articles/add-article-page";
    }

    @PostMapping("/add")
    public String addArticle( @ModelAttribute("articleDto") @Valid ArticleDto articleDto, BindingResult result) {
        if (result.hasErrors()) {
            return "articles/add-article-page";
        }

        articleService.saveArticle(articleDto);
        return redirect + "/articles";
    }

    @GetMapping("/edit/article-info-page/{id}")
    public String getInfoArticlePage(@PathVariable Long id, Model model, ArticleDto articleDto){
        model.addAttribute("articleDto", articleService.findById(id).get());
        return "articles/article-edit-page";
    }

    @PostMapping("/edit/{id}")
    public String editArticle(@PathVariable Long id, @ModelAttribute("articleDto") ArticleDto articleDto, BindingResult result){
        if(result.hasErrors()){
            return "articles/article-edit-page";
        }
        articleService.updateArticle(articleDto);
        return redirect + "/articles";
    }

    @GetMapping("/values/{id}")
    public String getValuesPage(@PathVariable Long id, Model model){
        model.addAttribute("articleCategoriesDto", articleService.getAllCategoriesAndValuesByArticleId(id));
        return "articles/article-values-page";
    }

    @GetMapping("/values/{id}/add/{valueId}")
    public String addCategory(@PathVariable(name = "id") Long articleId, @PathVariable(name = "valueId") Long valueID) {
        articleService.addValue(articleId, valueID);

        return redirect + "/articles/values/{id}";
    }

    @GetMapping("/values/{id}/delete/{valueId}")
    public String deleteCategory(@PathVariable(name = "id") Long articleId, @PathVariable(name = "valueId") Long valueID) {
        articleService.deleteValue(articleId, valueID);

        return redirect + "/articles/values/{id}";
    }


    @GetMapping("/delete/{id}")
    public String deleteArticleById(@PathVariable Long id){
        articleService.removeArticleById(id);
        return redirect + "/articles";
    }
}
