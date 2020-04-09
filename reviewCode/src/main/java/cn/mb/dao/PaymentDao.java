package cn.mb.dao;

import cn.mb.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper//用re那个可能会有问题
public interface PaymentDao {

    Payment getPaymentById(@Param("id") Long id);

    int save(Payment payment);

}
