package com.zhang.service.impl;

import com.zhang.product.bean.Product;
import com.zhang.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setProductname("iphone"+productId);
        product.setPrice(new BigDecimal("8888"));
        product.setNum(2);

        //try {
        //    TimeUnit.SECONDS.sleep(5);
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}

        return product;
    }
}
