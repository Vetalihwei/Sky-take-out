package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    List<Long> getsetmealIdByDishIds(List<Long> ids);

    void insertBatch(List<SetmealDish> setmealDishes);
}