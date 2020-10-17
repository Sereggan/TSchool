package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.tsystems.tschool.entity.Value;

import java.util.HashSet;
import java.util.Set;

/**
 * Category data transfer object
 * Class to access and control category
 */
@Setter
@Getter
public class CategoryDto {

    private Long id;

    @NotEmpty(message = "Title cant be empty")
    private String title;

    @NotEmpty(message = "Description cant be empty")
    private String description;

    private Set<Value> values = new HashSet<>();
}
