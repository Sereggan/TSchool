package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class CatalogDto {

    private List<CatalogArticleDto> catalogArticleDto = new ArrayList<>();
}
