package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import sun.jvm.hotspot.runtime.StaticBaseConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fuzq
 * @create
 */

@Component
public class Mylb implements Loadbalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("*********第几次访问，次数next:" + next);
        return next;
    }

    public ServiceInstance instances(List<ServiceInstance> instances) {

        int index = getAndIncrement() % instances.size();
        return instances.get(index);
    }

}
