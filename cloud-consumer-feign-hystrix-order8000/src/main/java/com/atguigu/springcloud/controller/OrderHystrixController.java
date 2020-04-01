package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.OrderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
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
@DefaultProperties(defaultFallback = "payment_global_fallback")
public class OrderHystrixController {

    @Resource
    private OrderHystrixService orderHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentinfo_OK(@PathVariable("id") int id) {
        return orderHystrixService.paymentinfo_OK(id);

    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentinfo_timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    public String paymentinfo_Timeout(@PathVariable("id") int id) {
        return orderHystrixService.paymentinfo_Timeout(id);
    }

    //paymentinfo_Timeout方法的兜底方法
    public String paymentinfo_timeoutHandler(@PathVariable("id") int id) {

        return "线程池：" + Thread.currentThread().getName() +
                "我是消费者80，对方服务提供端超时或者是自身系统出现异常";

    }

    //下面是全局方法，在这个类中如果没有个性定制降级方法，如果出现降级，就会调用这个方法
    public String payment_global_fallback() {
        return "系统运行异常";
    }


}
