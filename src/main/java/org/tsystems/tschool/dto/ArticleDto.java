package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ArticleDto {

    private Long id;

    @NotEmpty(message = "Title cant be empty")
    private String title;

    @NotNull(message = "Price cant be empty")
    @Min(value = 0,message = "Price cant be Negative")
    private Float price;

    @NotNull(message = "Quantity cant be empty")
    @Min(value = 0, message = "Quantity cant be Negative")
    private Integer quantity;
}
