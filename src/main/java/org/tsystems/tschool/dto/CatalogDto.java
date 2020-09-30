package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class CatalogDto {

    private Set<CatalogArticleDto> catalogArticleDto;
}
