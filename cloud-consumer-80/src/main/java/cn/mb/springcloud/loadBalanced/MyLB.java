package cn.mb.springcloud.loadBalanced;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
@Component  //  注入容器
public class MyLB implements LoadBalanced {

    //  访问次数
    private AtomicInteger totalCount = new AtomicInteger(0);

    @Override
    public ServiceInstance getInstance(List<ServiceInstance> instances) {
        int size = instances.size();
        //  没有服务，直接返回null
        if (size == 0) {
            return null;
        }
        //  有服务，获取应用服务的索引并设置访问总量
        Integer index = incrementAndGetModulo(size);
        //  这里还可以设置尝试，设置容错，一定次数获取不到对象就抛出异常信息
        return instances.get(index);
    }

    //  设置自旋锁，设置总的访问次数
    private Integer incrementAndGetModulo(Integer size) {
        while (true) {
            //  获取当前值
            int current = totalCount.get();
            //  获取期望值
            int next = current + 1;
            //  通过CAS设置总访问量
            if (totalCount.compareAndSet(current, next)) {
                //  返回实例下标
                return next % size;
            }
        }
    }
}
