package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.tsystems.tschool.enums.OrderStatus;

/**
 * Order status data transfer object
 * Class to access and control order
 */
@Setter
@Getter
public class OrderStatusDto {

    private Boolean isPaid;

    private OrderStatus orderStatus;
}
