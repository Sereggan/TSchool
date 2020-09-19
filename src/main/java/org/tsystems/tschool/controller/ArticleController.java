package org.tsystems.tschool.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.mapper.ArticleDtoMapper;
import org.tsystems.tschool.service.ArticleService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;

    private final ArticleDtoMapper mapper
            = Mappers.getMapper(ArticleDtoMapper.class);

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
        return "redirect:/articles";
    }

    @GetMapping("/edit/article-info-page/{id}")
    public String getEditArticlePage(@PathVariable Long id, Model model, ArticleDto articleDto){
        model.addAttribute("articleDto", articleService.findById(id));
        return "articles/article-info-page";
    }

    @PostMapping("/edit/{id}")
    public String editArticle(@PathVariable Long id, BindingResult result, @ModelAttribute("articleDto") ArticleDto articleDto){
        if(result.hasErrors()){
            return "/articles/article-info-page";
        }
//        Optional<ArticleDto> existingArticle = articleService.findById(id);
//        if(existingArticle.isPresent()){
//            articleService.updateArticle(articleDto);
//        }else {
//            articleService.saveArticle(articleDto);
//        }
        articleService.updateArticle(articleDto);
        return "redirect:/articles";
    }


    @DeleteMapping("/delete/${id}")
    public String deleteArticleById(@PathVariable Long id){
        articleService.removeArticleById(id);
        return "redirect:/articles";
    }
}
