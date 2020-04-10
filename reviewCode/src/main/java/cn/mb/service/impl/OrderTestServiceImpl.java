package cn.mb.service.impl;

import cn.mb.dao.OrderTestDao;
import cn.mb.entities.OrderTest;
import cn.mb.service.OrderTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderTestServiceImpl implements OrderTestService {

    @Autowired
    private OrderTestDao orderTestDao;

    @Override
    public int save(OrderTest order) {
        int result = 0;
        while(true) {
            /* ---------------这一部分大家都可以进入，可能会出现生成一样的订单编号--------------- */
            //  后台生成编号
            String orderNumber = UUID.randomUUID().toString();
            //  判断编号是否存在
            OrderTest orderTestByOrderNumber = orderTestDao.getOrderTestByOrderNumber(orderNumber);
            //  如果编号存在，就重新生成编号
            if (orderTestByOrderNumber != null) continue;
            //  不存在就插入该数据，如果该编号已被占用则会发生异常
            order.setOrderNumber(orderNumber);
            /* ---------------这一部分大家都可以进入，可能会出现生成一样的订单编号--------------- */
            /**
             * 执行插入，此时获取该对象锁才可以执行以下方法
             *      最快捷的同步就是直接修饰在方法上，但前面这些工作都可以在加锁前做，减小锁中代码，提高效率
             *      这里意思是：假设A、Bid一样，A线程拿到锁，开始插入，B等待，B插入后会发生异常，如果不上锁，
             *      两个线程一起执行，会插入两条id一样的数据
             */
            //  保证同一时间只有一个线程执行以下代码块
            synchronized (this) {
                try {
                    /**
                     * 这里可以再次判断该编号是否存在，如果别的线程已经插入该编号，直接continue
                     * 但这样这里就会再多一次数据库查询，如果能保证大部分情况不会重复，那么这里就没必要再次查询，
                     * 让异常来处理就可以了
                     */
                    //  获取到锁的线程就会执行成功，避免了单个服务的重复插入
                    result = orderTestDao.save(order);
                    break;
                } catch (Exception e) {
                    /**
                     * 异常捕获：
                     *      发生异常说明编号已存在
                     *      如果是集群服务，那么也要避免多个服务重复插入，如果重复插入直接捕获异常重新插入
                     */
                    continue;
                }
            }
        }
        return result;
    }
}
