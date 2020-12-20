package org.tsystems.tschool.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.dao.CartDAO;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.service.ArticleService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest()
@Import(ArticleDAO.class)
public class ArticleIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArticleDAO articleDAO;

    @Test
    public void testFindAll(){
        Article article = Article.builder().id(1L).quantity(1).price(1F).title("article").build();
        entityManager.merge(article);

        Article found = articleDAO.findById(article.getId());

        assertThat(found.getTitle())
                .isEqualTo(article.getTitle());
    }
}
