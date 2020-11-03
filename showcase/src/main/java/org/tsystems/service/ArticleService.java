package org.tsystems.service;


import org.tsystems.data.Article;

import java.util.List;

/**
 * Created by Sergey Nikolaychuk on 31.10.2020
 */

public interface ArticleService {

    public List<Article> getTopArticles();
}
