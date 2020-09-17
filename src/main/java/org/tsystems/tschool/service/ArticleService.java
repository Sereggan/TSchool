package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    public List findAll();

    public List findAllByOrderItem(OrderItem orderItem);

    public Optional<ArticleDto> findById(Long id);

    public void removeArticleById(Long id);

    public void saveArticle(ArticleDto articleDto);
}

