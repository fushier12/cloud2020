package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fuzq
 * @create
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentinfo_OK(@PathVariable("id") int id) {
        log.info("********" + serverPort);
        log.info(paymentService.paymentinfo_OK(id));
        return paymentService.paymentinfo_OK(id);

    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentinfo_Timeout(@PathVariable("id") int id) {
        log.info("********" + serverPort);
        log.info(paymentService.paymentinfo_timeout(id));
        return paymentService.paymentinfo_timeout(id);

    }

    @GetMapping(value = "/payment/hystrix/CircuitBreaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("********result:" + result);
        return result;

    }

}
