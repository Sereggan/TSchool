package org.tsystems.data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.tsystems.service.ArticleService;

import java.util.List;

/**
 * Created by Sergey Nikolaychuk on 02.11.2020
 */

@Setter
@Getter
@ApplicationScoped
@Named
public class ArticlesRepository {

    private List<Article> articles;

    @Inject
    ArticleService articleService;

    @PostConstruct
    public void getArticlesOnStart() {
        articles = articleService.getTopArticles();
    }
}
