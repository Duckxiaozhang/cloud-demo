package com.zhang.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "order")//配置批量绑定
@Data
public class OrderProperties {
    String timeout;
    String autoConfirm;
    String dbUrl;
}
