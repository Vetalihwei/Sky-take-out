package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    void saveWithDish(SetmealDTO setmealDTO);

    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    void deleteBatch(Long[] ids);

    void openOrstop(Integer status,Long id);

    void updateWithSetmealDish(SetmealDTO setmealDTO);

    SetmealVO getByIdWithDish(Long id);

    List<Setmeal> list(Setmeal setmeal);


    List<DishItemVO> getDishItemById(Long id);

}
