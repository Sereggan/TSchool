package org.tsystems.tschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Order item data transfer object
 * Class to access and control user data
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItemDto {

    private Long id;

    private String article;

    private Integer quantity;

    private Float price;
}
