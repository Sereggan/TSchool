package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.tsystems.tschool.enums.OrderStatus;

@Setter
@Getter
public class OrderStatusDto {

    private Boolean isPaid;

    private OrderStatus orderStatus;
}
