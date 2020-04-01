package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fuzq
 * @create
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule getIRule() {
        //定义为随机负载均衡算法
        return new RandomRule();
    }
}
