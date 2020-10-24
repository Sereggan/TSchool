package org.tsystems.tschool.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.dao.CartDAO;
import org.tsystems.tschool.dao.OrderDAO;
import org.tsystems.tschool.dao.UserDAO;
import org.tsystems.tschool.dto.CartDto;
import org.tsystems.tschool.dto.CartItemDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Cart;
import org.tsystems.tschool.entity.CartItem;
import org.tsystems.tschool.entity.User;
import org.tsystems.tschool.exception.ItemNotFoundException;
import org.tsystems.tschool.service.jpa.CartServiceImpl;

import javax.persistence.NoResultException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @DisplayName("Catch ItemNotFoundException in cart")
    @Test
    void addNewArticleCatchException() {
        when(articleDAO.findByIdWithLock(any(Long.class))).thenThrow(EmptyResultDataAccessException.class);
        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            cartService.addArticle(1L, 1L);
        });
    }

    @DisplayName("Add existing Article to Cart")
    @Test
    void addExistingArticle() {
        CartItem cartItem = CartItem.builder().cart(cart).article(article).id(1L).quantity(1).price(100F).build();
        cart.getCartItems().add(cartItem);
        when(cartDao.findById(any(Long.class))).thenReturn(cart);
        when(articleDAO.findByIdWithLock(any(Long.class))).thenReturn(article);
        cartService.addArticle(1L, 1L);
        assertEquals(3, article.getQuantity());
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

    @DisplayName("Add new Article to Cart Catch exception")
    @Test
    void addNewArticleInSessionCatchException() {
        when(articleDAO.findByIdWithLock(1L)).thenThrow(NoResultException.class);
        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            cartService.addArticleInSession(CartDto.builder().build(), 1L);
        });
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

    @DisplayName("Find existing cart by username")
    @Test
    void findByUsername() {
        when(cartDao.findByUsername("name")).thenReturn(cart);
        CartDto cartDto = cartService.findByUsername("name");
        assertEquals(cart.getId(), cartDto.getId());
    }

    @DisplayName("Create new cart")
    @Test
    void findByUsernameWithNull() {
        when(cartDao.findByUsername("name")).thenThrow(EmptyResultDataAccessException.class);
        when(userDAO.getUserByUsername("name")).thenReturn(User.builder().email("test").username("name").build());
        when(cartDao.save(any(Cart.class))).thenReturn(cart);
        CartDto cartDto = cartService.findByUsername("name");
        assertNotNull(cartDto);
    }

    @Disabled
    @Test
    void createOrder() {
    }

    @DisplayName("Remove article from cart")
    @Test
    void removeArticle() {
        CartItem cartItem = CartItem.builder().cart(cart).article(article).id(1L).quantity(4).price(100F).build();
        cart.getCartItems().add(cartItem);
        when(cartDao.findById(any(Long.class))).thenReturn(cart);
        when(articleDAO.findByIdWithLock(any(Long.class))).thenReturn(article);
        cartService.removeArticle(1L, 1L);
        assertEquals(3, cartItem.getQuantity());
        assertEquals(5, article.getQuantity());
    }

    @DisplayName("Remove last article from cart")
    @Test
    void removeLastArticle() {
        CartItem cartItem = CartItem.builder().cart(cart).article(article).id(1L).quantity(1).price(100F).build();
        cart.getCartItems().add(cartItem);
        when(cartDao.findById(any(Long.class))).thenReturn(cart);
        when(articleDAO.findByIdWithLock(any(Long.class))).thenReturn(article);
        cartService.removeArticle(1L, 1L);
        assertEquals(0, cart.getCartItems().size());
        assertEquals(5, article.getQuantity());
    }

    @DisplayName("Add items to database with empty cart in database")
    @Test
    void moveItemsFromSessionToDatabase() {
        CartItemDto cartItemDto = CartItemDto.builder().article(article.getTitle()).id(1L).articleId(1L)
                .quantity(2).price(100F).build();
        CartDto cartDto = CartDto.builder().totalCost(0F).cartItems(new HashSet<>()).build();
        cartDto.getCartItems().add(cartItemDto);
        when(cartDao.findByUsername(any(String.class))).thenReturn(cart);
        when(articleDAO.findById(any(Long.class))).thenReturn(article);
        CartDto newCartDto = cartService.moveItemsFromSessionToDatabase(cartDto, "name");
        assertNotNull(cart.getCartItems());
        assertEquals(cartDto.getCartItems(), newCartDto.getCartItems());
        assertEquals(4, article.getQuantity());
    }

    @DisplayName("Add items to database with empty cart in database and Empty cartDtoItems")
    @Test
    void moveItemsFromSessionToDatabaseWithEmptyDto() {
        CartDto cartDto = CartDto.builder().totalCost(0F).cartItems(new HashSet<>()).build();
        when(cartDao.findByUsername(any(String.class))).thenReturn(cart);
        CartDto newCartDto = cartService.moveItemsFromSessionToDatabase(cartDto, "name");
        assertNotNull(cart.getCartItems());
        assertEquals(cartDto.getCartItems(), newCartDto.getCartItems());
        assertEquals(4, article.getQuantity());
    }

    @DisplayName("Add items to database with not empty cart in database")
    @Test
    void moveItemsFromSessionToDatabaseWithNull() {
        CartItemDto cartItemDto = CartItemDto.builder().article(article.getTitle()).id(1L).articleId(1L)
                .quantity(2).price(100F).build();
        CartDto cartDto = CartDto.builder().totalCost(0F).cartItems(new HashSet<>()).build();
        cartDto.getCartItems().add(cartItemDto);
        when(cartDao.findByUsername(any(String.class))).thenReturn(cart);
        when(articleDAO.findById(any(Long.class))).thenReturn(article);
        CartDto newCartDto = cartService.moveItemsFromSessionToDatabase(cartDto, "name");
        assertNotNull(cart.getCartItems());
        assertEquals(cartDto.getCartItems(), newCartDto.getCartItems());
        assertEquals(4, article.getQuantity());
    }

    @DisplayName("Add items to database with null cartDto")
    @Test
    void moveItemsFromSessionToDatabaseWithNullCartDto() {
        when(cartDao.findByUsername(any(String.class))).thenReturn(cart);
        CartDto newCartDto = cartService.moveItemsFromSessionToDatabase(null, "name");
        assertEquals(cart.getId(), newCartDto.getId());
    }

    @DisplayName("Test clearSessionCart")
    @Test
    void clearSessionCart() {
        CartItem cartItem = CartItem.builder().cart(cart).article(article).id(1L).quantity(1).price(100F).build();
        cart.getCartItems().add(cartItem);
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
        assertDoesNotThrow(() -> {
            cartService.clearSessionCart(cartDto);
        });
    }
}