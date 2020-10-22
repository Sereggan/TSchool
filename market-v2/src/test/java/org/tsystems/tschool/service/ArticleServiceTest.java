package org.tsystems.tschool.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.dao.ValueDAO;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.dto.CatalogDto;
import org.tsystems.tschool.entity.*;
import org.tsystems.tschool.exception.ArticleAlreadyExistException;
import org.tsystems.tschool.exception.ItemNotFoundException;
import org.tsystems.tschool.service.jpa.ArticleServiceImpl;

import javax.persistence.NonUniqueResultException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    ArticleDAO articleDAO;

    @Mock
    ValueDAO valueDAO;

    @InjectMocks
    ArticleServiceImpl articleService;

    private List<Article> articleList = new ArrayList<>();

    private List<ArticleDto> articleDtoList = new ArrayList<>();

    private Article article;

    private ArticleDto articleDto;

    private Value value;

    @BeforeEach
    void setUp() {
        articleList.add(Article.builder().id(1L).price(100F).title("Article1").quantity(11).cartItem(new HashSet<>())
                .categories(new HashSet<>()).values(new HashSet<>()).build());
        articleList.add(Article.builder().id(2L).price(100F).title("Article2").quantity(11).cartItem(new HashSet<>())
                .categories(new HashSet<>()).values(new HashSet<>()).build());
        article = Article.builder().id(1L).price(100F).title("Article 1").categories(new HashSet<>()).quantity(10).build();
        articleDto = ArticleDto.builder().id(1L).price(100F).title("Article 1").quantity(10).build();
        value = Value.builder().id(1L).title("Value").category(Category.builder().id(1L).articleSet(new HashSet<>())
                .title("Category")
                .description("desc").title("Category").build()).build();
    }

    @DisplayName("Test findAll by size of list")
    @Test
    void findAllBySize() {
        when(articleDAO.findAll()).thenReturn(articleList);
        List<ArticleDto> articles = articleService.findAll();
        assertEquals(2, articles.size());
    }

    @DisplayName("Test findAll with empty list")
    @Test
    void findAllEmptyList() {
        when(articleDAO.findAll()).thenReturn(new ArrayList<>());
        List<ArticleDto> articles = articleService.findAll();
        assertEquals(0, articles.size());
    }

    @DisplayName("Test findAll with NotNull Cart Items")
    @Test
    void findAllWithNotNullCartItems() {
        HashSet<CartItem> cartItems = new HashSet<>();
        cartItems.add(CartItem.builder().id(1L).build());
        article = Article.builder().id(1L).price(100F).title("Article 3").categories(new HashSet<>())
                .cartItem(cartItems).quantity(10).build();
        articleList.add(article);
        when(articleDAO.findAll()).thenReturn(articleList);
        List<ArticleDto> articles = articleService.findAll();
        int counter = 0;
        for (ArticleDto articleDto : articles) {
            if (articleDto.getIsActive().equals(true)) {
                counter += 1;
            }
        }
        assertEquals(2, counter);
        assertEquals(3, articles.size());
    }


    @DisplayName("Find existing article by id")
    @Test
    void findById() {
        when(articleDAO.findById(1L)).thenReturn(article);
        ArticleDto articleDto = ArticleDto.builder().id(1L).price(100F).title("Article 1").quantity(10).build();
        assertEquals(articleService.findById(1L), articleDto);
    }

    @DisplayName("Catch EmptyResultDataAccessException")
    @Test
    void findByIdCatchException() {
        when(articleDAO.findById(1L)).thenThrow(EmptyResultDataAccessException.class);
        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            articleService.findById(1L);
        });
    }

    @DisplayName("Delete by id")
    @Test
    void removeArticleById() {
        final Long id = 1L;
        articleService.removeArticleById(id);
        verify(articleDAO, times(1)).removeById(id);
    }

    @DisplayName("Catch EmptyResultDataAccessException")
    @Test
    void removeArticleCatchException() {
        final Long id = 2L;
        when(articleDAO.removeById(id)).thenThrow(EmptyResultDataAccessException.class);

        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            articleService.removeArticleById(id);
        });
    }

    @DisplayName("Save article")
    @Test
    void saveArticle() {
        when(articleDAO.save(any(Article.class))).thenReturn(article);
        ArticleDto savedArticle = articleService.saveArticle(articleDto);
        assertEquals(articleDto, savedArticle);
    }

    @DisplayName("Catch ArticleAlreadyExistException exception")
    @Test
    void saveArticleCatchException() {
        when(articleDAO.save(article)).thenThrow(NonUniqueResultException.class);

        Assertions.assertThrows(ArticleAlreadyExistException.class, () -> {
            articleService.saveArticle(articleDto);
        });
    }

    @Test
    void updateArticle() {
        when(articleDAO.findByIdWithLock(article.getId())).thenReturn(article);
        ArticleDto updatedArticle = articleService.updateArticle(articleDto);
        assertNotNull(updatedArticle);
        assertEquals(article.getTitle(), updatedArticle.getTitle());
        assertEquals(article.getPrice(), updatedArticle.getPrice());
        assertEquals(article.getQuantity(), updatedArticle.getQuantity());
    }

    @Test
    @Disabled(value = "Will make in future")
    void getAllCategoriesAndValuesByArticleId() {
    }


    @DisplayName("Test catch Exception when article doesnt exist")
    @Test
    void getAllCategoriesAndValuesByArticleIdCatchException() {
        when(articleDAO.findById(1L)).thenThrow(EmptyResultDataAccessException.class);
        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            articleService.getAllCategoriesAndValuesByArticleId(1L);
        });
    }

    @Test
    @DisplayName("Test add value to article")
    void addValue() {
        Set<Value> valueSet = new HashSet<>();
        article.setValues(valueSet);
        when(articleDAO.findById(1L)).thenReturn(article);
        when(valueDAO.findById(1L)).thenReturn(value);
        articleService.addValue(1L, 1L);
        assertTrue(article.getValues().contains(value));
    }

    @Test
    @DisplayName("Test catch Exception when article doesnt exist")
    void addValueArticleNotFound() {
        when(articleDAO.findById(1L)).thenThrow(EmptyResultDataAccessException.class);
        when(valueDAO.findById(1L)).thenReturn(value);
        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            articleService.addValue(1L, 1L);
        });
    }

    @Test
    @DisplayName("Test catch Exception when value doesnt exist")
    void addValueValueNotFound() {
        when(valueDAO.findById(1L)).thenThrow(EmptyResultDataAccessException.class);
        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            articleService.addValue(1L, 1L);
        });
    }

    @Test
    void deleteValue() {
        Set<Value> valueSet = new HashSet<>();
        Set<Article> articles = new HashSet<>();
        articles.add(article);
        value.setArticles(articles);
        valueSet.add(value);
        article.setValues(valueSet);
        when(articleDAO.findById(1L)).thenReturn(article);
        when(valueDAO.findById(1L)).thenReturn(value);
        articleService.deleteValue(1L, 1L);
        assertFalse(article.getValues().contains(value));
    }

    @DisplayName("Test catch Exception when article doesnt exist")
    @Test
    void deleteValueArticleDoesntExist() {
        when(articleDAO.findById(1L)).thenThrow(EmptyResultDataAccessException.class);
        when(valueDAO.findById(1L)).thenReturn(value);
        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            articleService.deleteValue(1L, 1L);
        });
    }

    @DisplayName("Test catch Exception when value doesnt exist")
    @Test
    void deleteValueValueDoesntExist() {
        when(valueDAO.findById(1L)).thenThrow(EmptyResultDataAccessException.class);
        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            articleService.deleteValue(1L, 1L);
        });
    }

    @Test
    @DisplayName("Test get catalog")
    void getCatalog() {
        articleList.add(Article.builder().id(1L).price(100F).title("Article 3").categories(new HashSet<>())
                .cartItem(new HashSet<>()).quantity(0).build());
        when(articleDAO.findAll()).thenReturn(articleList);
        CatalogDto catalogDto = articleService.getCatalog();
        assertEquals(2, catalogDto.getCatalogArticleDto().size());
    }

    @Test
    @DisplayName("Test get rating")
    void getArticlesRating() {
        when(articleDAO.findBestSellers(any(Long.class))).thenReturn(Arrays.asList(ArticleRating.builder().id(1L).build()));
        assertEquals(1, articleService.getArticlesRating().size());
    }
}