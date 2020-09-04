package org.tsystems.tschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Good {

    private String title;

    private int price;

    private String Category;

    private String brand;

    private int power;

    private String color;

    private int quantity;
}
