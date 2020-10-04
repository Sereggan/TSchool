package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.tsystems.tschool.entity.Address;
import org.tsystems.tschool.entity.OrderItem;
import org.tsystems.tschool.entity.User;
import org.tsystems.tschool.enums.DeliveryMethod;
import org.tsystems.tschool.enums.OrderStatus;
import org.tsystems.tschool.enums.PaymentMethod;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private Long price;
}
