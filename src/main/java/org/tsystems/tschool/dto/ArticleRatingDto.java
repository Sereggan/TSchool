package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class ArticleRatingDto implements  Comparable<ArticleRatingDto>{

    private String article;

    private Integer quantity;

    private Float price;

    private Float totalIncome;

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

    @Override
    public int compareTo(ArticleRatingDto o) {
        return o.getTotalIncome().compareTo(this.getTotalIncome());
    }
}
