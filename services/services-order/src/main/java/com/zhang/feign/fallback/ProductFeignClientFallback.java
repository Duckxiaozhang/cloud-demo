package com.zhang.feign.fallback;

import com.zhang.feign.ProductFeignClient;
import com.zhang.product.bean.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Product getProductById(Long id) {
        System.out.println("兜底回调");
        Product product = new Product();
        product.setProductname("商品不存在");
        product.setNum(0);
        product.setPrice(new BigDecimal("0"));
        product.setId(id);


        return product;
    }
}
