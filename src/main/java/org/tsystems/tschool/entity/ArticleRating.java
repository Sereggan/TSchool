package org.tsystems.tschool.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Represents rating of article
 */
@Setter
@Getter
@Entity
public class ArticleRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String article;

    private Float price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleRating)) return false;
        ArticleRating ratingDto = (ArticleRating) o;
        return getArticle().equals(ratingDto.getArticle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArticle());
    }
}
