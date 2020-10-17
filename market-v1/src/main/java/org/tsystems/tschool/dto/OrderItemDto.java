package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Order item data transfer object
 * Class to access and control user data
 */
@Setter
@Getter
public class OrderItemDto {

    private Long id;

    private String article;

    private Integer quantity;

    private Float price;
}
