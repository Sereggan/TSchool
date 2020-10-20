package org.tsystems.tschool.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.dao.CartDAO;
import org.tsystems.tschool.dao.OrderDAO;
import org.tsystems.tschool.dao.UserDAO;
import org.tsystems.tschool.dto.CartDto;
import org.tsystems.tschool.dto.CartItemDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Cart;
import org.tsystems.tschool.entity.CartItem;
import org.tsystems.tschool.service.jpa.CartServiceImpl;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    CartDAO cartDao;

    @Mock
    UserDAO userDAO;

    @Mock
    ArticleDAO articleDAO;

    @Mock
    OrderDAO orderDAO;

    @InjectMocks
    CartServiceImpl cartService;

    private Cart cart;

    private Article article;

    @BeforeEach
    void setUp() {
        cart = Cart.builder().id(1L).totalCost(0F).cartItems(new HashSet<>()).build();
        article = Article.builder().id(1L).price(100F).quantity(4).title("Title 1").build();
    }

    @DisplayName("Add new Article to Cart")
    @Test
    void addNewArticle() {
        when(cartDao.findById(any(Long.class))).thenReturn(cart);
        when(articleDAO.findByIdWithLock(any(Long.class))).thenReturn(article);
        CartDto cartDto = cartService.addArticle(1L, 1L);
        assertTrue(cartDto.getCartItems().contains(CartItemDto.builder().articleId(1L).build()));
    }

    @DisplayName("Add existing Article to Cart")
    @Test
    void addExistingArticle() {
        CartItem cartItem = CartItem.builder().cart(cart).article(article).id(1L).quantity(1).price(100F).build();
        cart.getCartItems().add(cartItem);
        when(cartDao.findById(any(Long.class))).thenReturn(cart);
        when(articleDAO.findByIdWithLock(any(Long.class))).thenReturn(article);
        cartService.addArticle(1L, 1L);
        assertEquals(2, cartItem.getQuantity());
    }

    @DisplayName("Add new Article to Cart in session")
    @Test
    void addNewArticleInSession() {
        CartDto cartDto = CartDto.builder().totalCost(0F).cartItems(new HashSet<>()).build();
        when(articleDAO.findByIdWithLock(any(Long.class))).thenReturn(article);
        cartService.addArticleInSession(cartDto, 1L);
        assertEquals(3, article.getQuantity());
        assertEquals(1, cartDto.getCartItems().size());
    }

    @DisplayName("Add existing Article to Cart")
    @Test
    void addExistingArticleInSession() {
        CartItemDto cartItemDto = CartItemDto.builder().article(article.getTitle()).id(1L).articleId(1L)
                .quantity(1).price(100F).build();
        CartDto cartDto = CartDto.builder().totalCost(0F).cartItems(new HashSet<>()).build();
        cartDto.getCartItems().add(cartItemDto);
        when(articleDAO.findByIdWithLock(any(Long.class))).thenReturn(article);
        cartService.addArticleInSession(cartDto, 1L);
        assertEquals(3, article.getQuantity());
        assertEquals(1, cartDto.getCartItems().size());
        assertTrue(cartDto.getCartItems().contains(cartItemDto));
    }

    @DisplayName("Remove Not last Article from cart in session")
    @Test
    void removeArticleInSession() {
        CartItemDto cartItemDto = CartItemDto.builder().article(article.getTitle()).id(1L).articleId(1L)
                .quantity(2).price(100F).build();
        CartDto cartDto = CartDto.builder().totalCost(0F).cartItems(new HashSet<>()).build();
        cartDto.getCartItems().add(cartItemDto);
        when(articleDAO.findByIdWithLock(any(Long.class))).thenReturn(article);
        cartService.removeArticleInSession(cartDto, 1L);
        assertEquals(5, article.getQuantity());
        assertEquals(1, cartDto.getCartItems().size());
        assertTrue(cartDto.getCartItems().contains(cartItemDto));
    }

    @DisplayName("Remove  last Article from cart in session")
    @Test
    void removeLastArticleInSession() {
        CartItemDto cartItemDto = CartItemDto.builder().article(article.getTitle()).id(1L).articleId(1L)
                .quantity(1).price(100F).build();
        CartDto cartDto = CartDto.builder().totalCost(0F).cartItems(new HashSet<>()).build();
        cartDto.getCartItems().add(cartItemDto);
        when(articleDAO.findByIdWithLock(any(Long.class))).thenReturn(article);
        cartService.removeArticleInSession(cartDto, 1L);
        assertEquals(5, article.getQuantity());
        assertEquals(0, cartDto.getCartItems().size());
        assertFalse(cartDto.getCartItems().contains(cartItemDto));
    }

    @Test
    void findByUsername() {
    }

    @Test
    void createOrder() {
    }

    @Test
    void removeArticle() {
    }

    @Test
    void addItemsToDatabase() {
    }

    @DisplayName("Test clearSessionCart")
    @Test
    void clearSessionCart() {
        CartDto cartDto = CartDto.builder().totalCost(0F).cartItems(new HashSet<>()).build();
        cartDto.getCartItems().add(CartItemDto.builder().article(article.getTitle()).id(1L).articleId(1L)
                .quantity(2).price(100F).build());
        when(articleDAO.findByIdWithLock(any(Long.class))).thenReturn(article);
        cartService.clearSessionCart(cartDto);
        assertEquals(6, article.getQuantity());

    }

    @DisplayName("Test clearSessionCart with empty cartItems")
    @Test
    void clearSessionCartWithEmptyItems() {
        CartDto cartDto = CartDto.builder().totalCost(0F).cartItems(new HashSet<>()).build();
        assertDoesNotThrow( ()-> {
            cartService.clearSessionCart(cartDto);
        });
    }
}