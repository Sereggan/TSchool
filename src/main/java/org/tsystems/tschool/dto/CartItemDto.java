package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public class CartItemDto implements Serializable {

    private Long id;

    private String article;

    private Long articleId;

    private Integer quantity;

    private Float price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemDto)) return false;
        CartItemDto that = (CartItemDto) o;
        return getArticleId().equals(that.getArticleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArticleId());
    }
}
