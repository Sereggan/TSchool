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
import java.util.List;

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
    public String addArticle( @ModelAttribute("articleDto") @Valid ArticleDto articleDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "articles/add-article-page";
        }
//        Article article = new Article();
//        article.setId(articleDto.getId());
//        article.setPrice(articleDto.getPrice());
//        article.setQuantity(articleDto.getQuantity());
//        article.setTitle(articleDto.getTitle());

        articleService.saveArticle(articleDto);
        return "redirect:/articles";
    }
//
//    @PostMapping("/add")
//    public String addArticle( @ModelAttribute("articleDto") @Valid ArticleDto articleDto, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "articles/add-article-page";
//        }
//        articleService.saveArticle(articleDto);
//        return "redirect:/articles";
//    }

    @GetMapping("/edit/{id}")
    public String editArticle(@PathVariable Long id, Model model){
        model.addAttribute(articleService.findById(id));
        return "articles/edit-article-page";
    }

}
