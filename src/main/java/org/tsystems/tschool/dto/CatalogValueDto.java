package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Setter
@Getter
public class CatalogValueDto {

    private Long id;

    @NotEmpty(message = "Value cant be empty")
    private String value;

}
