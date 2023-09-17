package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    List<Long> getsetmealIdByDishIds(List<Long> ids);

    void insertBatch(List<SetmealDish> setmealDishes);

    void deleteBatchBySetmealId(Long[] ids);

    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void delteBySetmealId(Long setmealId);

    @Select("select * from setmeal_dish where setmeal_id =#{id}")
    List<SetmealDish> getBySetmealId(Long id);

    Integer countStopDishBySetmealId(Long setmealId);
}
