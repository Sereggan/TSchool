package org.tsystems.tschool.dto;

import lombok.NoArgsConstructor;
import org.tsystems.tschool.entity.Order;

import java.io.Serializable;

@NoArgsConstructor
public class OrderDto implements Serializable {

    public OrderDto(Order order) {
    }
}
