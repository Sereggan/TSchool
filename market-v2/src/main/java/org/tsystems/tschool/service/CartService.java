package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.*;

/**
 * The interface Category to access and control Cart business logic.
 */
public interface CartService {

    /**
     * Add article cart dto.
     *
     * @param cartId    the cart id
     * @param articleId the article id
     * @return the cart transfer object
     */
    public CartDto addArticle(Long cartId, Long articleId);

    /**
     * Add article in session to cart
     *
     * @param cartDto    the cart dto
     * @param articleId  article id
     * @return the cart transfer object
     */
    public CartDto addArticleInSession(CartDto cartDto, Long articleId);

    /**
     * Remove article from cart in session
     *
     * @param cartDto the cart dto
     * @param id      the id of article
     * @return the cart transfer object
     */
    public CartDto removeArticleInSession(CartDto cartDto, Long id);

    /**
     * Find cart by username
     *
     * @param name the username
     * @return the cart transfer object
     */
    CartDto findByUsername(String name);

    /**
     * Create order.
     *
     * @param cartDto         the cart transfer object
     * @param orderDetailsDto the order details transfer object
     */
    public void createOrder(CartDto cartDto, OrderDetailsDto orderDetailsDto);

    /**
     * Remove article cart dto.
     *
     * @param cartId the cart id
     * @param id     the article id
     * @return the cart dto
     */
    public CartDto removeArticle(Long cartId, Long id);

    /**
     * Add items to cart and saves them in database
     *
     * @param cartDto  the cart transfer object
     * @param username the username
     * @return the cart transfer object
     */
    public CartDto addItemsToDatabase(CartDto cartDto, String username);

    /**
     * Clear cart in session.
     *
     * @param cartDto the cart transfer object
     */
    public void clearSessionCart(CartDto cartDto);
}
