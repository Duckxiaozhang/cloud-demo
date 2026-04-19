package com.zhang.product.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Long id;
    private BigDecimal price;
    private String Productname;
    private int num;

}
