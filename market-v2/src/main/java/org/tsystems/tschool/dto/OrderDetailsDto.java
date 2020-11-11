package org.tsystems.tschool.dto;

import lombok.*;
import org.tsystems.tschool.enums.DeliveryMethod;
import org.tsystems.tschool.enums.PaymentMethod;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * The type Order details dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class OrderDetailsDto {

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    private AddressDto addressDto;
}
