package com.zhang.controller;

import com.zhang.feign.ProductFeignClient;
import com.zhang.order.bean.Order;
import com.zhang.properties.OrderProperties;
import com.zhang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//@RefreshScope//自动刷新的注解 不需要重启服务
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;


//    @Value("${order.timeout}")
//    String orderTimeout;
//    @Value("${order.auto-confirm}")
//    String orderAutoConfirm;

    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/config")
    public String config() {
        return "order.timeout=" + orderProperties.getTimeout() + ";" +
                "order.auto-confirm=" + orderProperties.getAutoConfirm()+";"+
                "order.db-url="+orderProperties.getDbUrl();
    }

    @GetMapping("/create")
    public Order createOrder(@RequestParam("productId") Long productId,
                             @RequestParam("userId") Long userId) {
        return orderService.createOrder(productId, userId);
    }
}
