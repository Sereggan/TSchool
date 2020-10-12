package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class CatalogArticleDto implements Comparable<CatalogArticleDto>{

    private Long id;

    private String title;

    private Float price;

    private Integer quantity;

    private List<CatalogValueDto> values = new ArrayList<>();

    @Override
    public int compareTo(CatalogArticleDto o) {
        return o.getPrice().compareTo(this.getPrice());
    }
}
