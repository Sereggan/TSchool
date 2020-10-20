package org.tsystems.tschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import org.tsystems.tschool.entity.Value;

import java.util.HashSet;
import java.util.Set;

/**
 * Category data transfer object
 * Class to access and control category
 */
@AllArgsConstructor
@NoArgsConstructor
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
