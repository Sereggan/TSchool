package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.*;
import org.tsystems.tschool.entity.Cart;

import java.util.List;

public interface CartService {

    public CartDto addArticle(Long cartId, Long articleId);

    public CartDto addArticleInSession(CartDto cartDto, ArticleDto articleDto);

    public CartDto removeArticleInSession(CartDto cartDto, Long id);

    CartDto findByUsername(String name);

    public void createOrder(CartDto cartDto, OrderDetailsDto orderDetailsDto);

    public void removeArticle(Long cartId, Long id);

    public CartDto addItemsToDatabase(CartDto cartDto, String username);
}
