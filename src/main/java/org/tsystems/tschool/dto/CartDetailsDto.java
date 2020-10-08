package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.tsystems.tschool.enums.DeliveryMethod;
import org.tsystems.tschool.enums.OrderStatus;
import org.tsystems.tschool.enums.PaymentMethod;

import javax.persistence.*;

@Setter
@Getter
public class CartDetailsDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    private AddressDto addressDto;
}

