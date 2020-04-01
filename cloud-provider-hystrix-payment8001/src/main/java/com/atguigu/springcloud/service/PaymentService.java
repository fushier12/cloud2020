package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author fuzq
 * @create
 */
@Service
public class PaymentService {

    //-------------服务降级--------------
    public String paymentinfo_OK(int id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentinfo_OK" + "O(∩_∩)O哈哈~" + id;
    }

    //兜底的处理是调用方法paymentinfo_timeoutHandler，设置超时超时触发的时间3s
    @HystrixCommand(fallbackMethod = "paymentinfo_timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentinfo_timeout(int id) {
//        int age = 10 / 0;
        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "O(∩_∩)O哈哈~" + "成功" + id;
    }

    public String paymentinfo_timeoutHandler(int id) {

        return "线程池：" + Thread.currentThread().getName() +
                "8001系统繁忙或者出错，请稍后重试" + "\t" + "失败o(╥﹏╥)o" + id;

    }
    //----------服务熔断---------
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("*******id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;

    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id不能为负数，请稍后再试" + "\t id:" + id;
    }

}