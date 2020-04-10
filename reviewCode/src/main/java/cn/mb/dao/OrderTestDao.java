package cn.mb.dao;

import cn.mb.entities.OrderTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderTestDao {
    int save(OrderTest order);

    OrderTest getOrderTestByOrderNumber(@Param("orderNumber") String orderNumber);
}
