package com.sky.mapper;


import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.core.annotation.Order;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {


    void insert(Orders orders);

    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    void update(Orders orders);

    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    @Select("select count(*) from orders where status = #{status}")
    Integer countOrderStatus(Integer status);

    @Select("select * from orders where id = #{id}")
    Orders getById(Long id);

    @Select("select * from orders where  status = #{status} and order_time < #{orderTime}")
    List<Orders> getBystatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);
}
