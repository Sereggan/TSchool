package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class CartItemDto implements Serializable {

    private Long id;

    private String article;

    private Long articleId;

    private Integer quantity;
}
