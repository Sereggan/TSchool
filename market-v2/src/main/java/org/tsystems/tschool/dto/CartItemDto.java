package org.tsystems.tschool.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Cart item data transfer object
 * Class to access and control user data
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
