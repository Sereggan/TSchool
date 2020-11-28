package org.tsystems.tschool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tsystems.tschool.controller.ArticleController;
import org.tsystems.tschool.service.ArticleService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TschoolApplication.class)
public class TschoolApplicationTest {

    @Autowired
    ArticleController articleController;

    @Autowired
    ArticleService articleService;

    @Test
    public void contextLoads(){
        articleService.findAll();
        assertThat(articleService).isNotNull();
    }
}
