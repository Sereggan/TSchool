package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.tsystems.tschool.entity.CartItem;
import org.tsystems.tschool.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class CartDto implements Serializable {

    private Long id;

    private Long userId;

    private Set<CartItemDto> cartItems = new HashSet<>();

    private Float totalCost = 0F;
}
