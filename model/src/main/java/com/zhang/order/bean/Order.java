package com.zhang.order.bean;

import com.zhang.product.bean.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class Order {
    private Long id;
    private BigDecimal totalAmount;
    private Long userId;
    private String nikeName;
    private String address;
    private List<Product> productList;
}
