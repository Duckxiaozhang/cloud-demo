package com.zhang.service;

import com.zhang.order.bean.Order;

public interface OrderService {

    Order createOrder(Long productId, Long userId);
}
