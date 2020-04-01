package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author fuzq
 * @create
 */
@Component
public class PaymentFallbackService implements OrderHystrixService {

    @Override
    public String paymentinfo_Timeout(int id) {
        return "PaymentFallbackService" + "\t" + "paymentinfo_Timeout" + "o(╥﹏╥)o";
    }

    @Override
    public String paymentinfo_OK(int id) {
        return "PaymentFallbackService" + "\t" + "paymentinfo_OK" + "o(╥﹏╥)o";
    }
}
