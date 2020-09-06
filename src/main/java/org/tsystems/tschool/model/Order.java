package org.tsystems.tschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    //private User user;

    private Address address;

    private String paymentMethod;

    private String deliveryMethod;

    private List<Box> boxList;

    private String PaymentStatus;

    private String orderStatus;
}
