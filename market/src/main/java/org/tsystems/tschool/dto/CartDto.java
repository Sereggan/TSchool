package org.tsystems.tschool.dto;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Cart data transfer object
 * Class to access and control user data
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CartDto implements Serializable {

    private Long id;

    private Long userId;

    private Set<CartItemDto> cartItems = new HashSet<>();

    private Float totalCost = 0F;
}
