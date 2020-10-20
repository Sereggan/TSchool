package org.tsystems.tschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Catalog and value data transfer object
 * Class to access and control catalog
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CatalogValueDto {

    private Long id;

    private String title;

    private String category;
}
