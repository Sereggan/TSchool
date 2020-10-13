package org.tsystems.tschool.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Setter
@Getter
@Entity
public class ArticleRatingDto{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String article;

    private Float price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleRatingDto)) return false;
        ArticleRatingDto ratingDto = (ArticleRatingDto) o;
        return getArticle().equals(ratingDto.getArticle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArticle());
    }
}
