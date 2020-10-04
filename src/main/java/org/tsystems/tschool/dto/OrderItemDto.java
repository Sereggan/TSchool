package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemDto {

    private Long id;

    private String article;

    private Integer quantity;

    private Long price;
}
