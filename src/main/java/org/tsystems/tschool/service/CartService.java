package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.*;

public interface CartService {

    public CartDto addArticle(Long cartId, Long articleId);

    public CartDto addArticleInSession(CartDto cartDto, ArticleDto articleDto);

    public CartDto removeArticleInSession(CartDto cartDto, Long id);

    CartDto findByUsername(String name);

    public void createOrder(CartDto cartDto, OrderDetailsDto orderDetailsDto);

    public CartDto removeArticle(Long cartId, Long id);

    public CartDto addItemsToDatabase(CartDto cartDto, String username);

    public void clearSessionCart(CartDto cartDto);
}
