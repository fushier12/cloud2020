package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author fuzq
 * @create
 */

public interface Loadbalancer {
    //获得机器总数
    public ServiceInstance instances (List<ServiceInstance> serviceInstances);



}
