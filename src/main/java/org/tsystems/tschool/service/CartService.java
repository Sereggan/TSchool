package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.dto.CartDto;
import org.tsystems.tschool.dto.CartItemDto;
import org.tsystems.tschool.entity.Cart;

import java.util.List;

public interface CartService {

    public CartDto addArticle(CartDto cartDto, ArticleDto articleDto);

    public CartDto addArticleInSession(CartDto cartDto, ArticleDto articleDto);

    CartDto findByUsername(String name);
}
