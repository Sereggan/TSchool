package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class CatalogArticleDto {

    private Long id;

    private String title;

    private Float price;

    private Integer quantity;

    private Set<CatalogValueDto> values = new HashSet<>();

}
