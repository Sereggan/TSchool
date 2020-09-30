package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.tsystems.tschool.entity.Value;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CatalogCategoryDto {

    private Long id;

    @NotEmpty(message = "Title cant be empty")
    private String title;

    private Set<Value> values = new HashSet<>();
}
