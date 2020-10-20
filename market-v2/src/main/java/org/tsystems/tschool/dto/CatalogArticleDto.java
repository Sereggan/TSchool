package org.tsystems.tschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

/**
 * Catalog and article data transfer object
 * Class to access and control catalog
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CatalogArticleDto implements Comparable<CatalogArticleDto> {

    private Long id;

    private String title;

    private Float price;

    private Integer quantity;

    private List<CatalogValueDto> values = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CatalogArticleDto)) return false;
        CatalogArticleDto that = (CatalogArticleDto) o;
        return getTitle().equals(that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    @Override
    public int compareTo(CatalogArticleDto o) {
        return o.getPrice().compareTo(this.getPrice());
    }
}
