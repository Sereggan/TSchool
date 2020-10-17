package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Catalog and value data transfer object
 * Class to access and control catalog
 */
@Setter
@Getter
public class CatalogValueDto {

    private Long id;

    private String title;

    private String category;
}
