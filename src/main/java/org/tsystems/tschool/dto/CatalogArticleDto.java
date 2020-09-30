package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class CatalogArticleDto {

    private Long id;

    @NotEmpty(message = "Title cant be empty")
    private String title;

    @NotNull(message = "Price cant be empty")
    @Min(value = 0,message = "Price cant be Negative")
    private Float price;

    @NotNull(message = "Quantity cant be empty")
    @Min(value = 0, message = "Quantity cant be Negative")
    private Integer quantity;

    private Set<CategoryDto> categories = new HashSet<>();

}
