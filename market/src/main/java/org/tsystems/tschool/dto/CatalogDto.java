package org.tsystems.tschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Catalog data transfer object
 * Class to access and control catalog
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CatalogDto {

    private List<CatalogArticleDto> catalogArticleDto = new ArrayList<>();
}
