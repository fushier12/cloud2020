package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author fuzq
 * @create
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallbackService.class)
public interface OrderHystrixService {
    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentinfo_Timeout(@PathVariable("id") int id);

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentinfo_OK(@PathVariable("id") int id);
}
