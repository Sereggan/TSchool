package org.tsystems.data;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Sergey Nikolaychuk on 31.10.2020
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Article implements Comparable<Article>, Serializable {

    private Long id;

    private String title;

    private Float price;

    private Integer quantity;

    private Boolean isActive = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article that = (Article) o;
        return getTitle().equals(that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    @Override
    public int compareTo(Article o) {
        return o.getPrice().compareTo(this.getPrice());
    }
}