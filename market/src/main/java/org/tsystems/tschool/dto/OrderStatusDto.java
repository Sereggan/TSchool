package org.tsystems.tschool.dto;

import lombok.*;
import org.tsystems.tschool.enums.OrderStatus;

/**
 * Order status data transfer object
 * Class to access and control order
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class OrderStatusDto {

    private Boolean isPaid;

    private OrderStatus orderStatus;
}
