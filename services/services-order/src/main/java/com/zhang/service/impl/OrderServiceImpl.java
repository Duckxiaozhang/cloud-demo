package com.zhang.service.impl;

import com.zhang.feign.ProductFeignClient;
import com.zhang.order.bean.Order;
import com.zhang.product.bean.Product;
import com.zhang.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    ProductFeignClient productFeignClient;

    @Override
    public Order createOrder(Long productId, Long userId) {

        //Product product = getProductFromRemoto(productId);
        Product product = productFeignClient.getProductById(productId);

        Order order = new Order();
        order.setUserId(userId);
        order.setId(1L);
        order.setNikeName("张三");
        order.setAddress("上海");
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setProductList(List.of(product));
        return order;
    }
    private Product getProductFromRemoto(Long productId){
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");

        ServiceInstance instance = instances.get(0);
        //http://localhost:9001/product/1 远程的url地址
        String url = "http://"+instance.getHost() + ":" + instance.getPort()+ "/product/" + productId;
        log.info("远程请求:{}",url);
        //给远程发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
    //完成负载均衡发送请求
    private Product getProductFromRemotoWithLoadBalance(Long productId){
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        //http://localhost:9001/product/1 远程的url地址
        String url = "http://"+choose.getHost() + ":" + choose.getPort()+ "/product/" + productId;
        log.info("远程请求:{}",url);
        //给远程发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    //基于注解的负载均衡
    private Product getProductFromRemotoWithLoadBalanceAnnotation(Long productId){
        String url = "http://service-product/product/" + productId;
        //给远程发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
}
