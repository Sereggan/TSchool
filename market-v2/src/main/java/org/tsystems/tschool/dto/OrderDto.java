package org.tsystems.tschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tsystems.tschool.enums.DeliveryMethod;
import org.tsystems.tschool.enums.OrderStatus;
import org.tsystems.tschool.enums.PaymentMethod;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Order data transfer object
 * Class to access and control user data
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDto {

    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    private AddressDto addressDto;

    private List<OrderItemDto> orderItems = new ArrayList<>();

    private Boolean isPaid;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Float price;
}
