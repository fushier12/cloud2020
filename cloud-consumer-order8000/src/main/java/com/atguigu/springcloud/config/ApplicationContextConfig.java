package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author fuzq
 * @create
 */
@Configuration
public class ApplicationContextConfig {

    //此处@Bean将RestTemplate加入到spring容器中
    @Bean
    //使用LoadBalanced赋予RestTemplate负载均衡的能力
//    @LoadBalanced
    public RestTemplate getRestTemplate() {

        return new RestTemplate();
    }
}
