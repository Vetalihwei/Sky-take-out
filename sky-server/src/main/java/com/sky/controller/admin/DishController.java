package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "菜品管理")
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    DishService dishService;


    @Operation(summary = "根据分类id查询菜品")
    @GetMapping("/list")
    public Result<List<Dish>> list(Long categoryId){
        List<Dish> list=dishService.getByCategoryId(categoryId);
        return Result.success(list);
    }


    @Operation(summary = "修改菜品")
    @PutMapping
    public Result<String> update(@RequestBody DishDTO dishDTO){
        dishService.updateWithFlavor(dishDTO);
        return Result.success("修改成功");
    }

    @Operation(summary = "根据id查询菜品")
    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable  Long id){
        DishVO dishVO= dishService.getByIdWithFlavor(id);

        return Result.success(dishVO);
    }


    @Operation(summary = "批量删除菜品")
    @DeleteMapping
    public Result<String> delete(@RequestParam List<Long> ids){
        dishService.deleteBatch(ids);
        return Result.success("删除成功");
    }

    @Operation(summary = "菜品分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(@ParameterObject DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult=dishService.page(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @Operation(summary = "新增菜品")
    @PostMapping
    public Result<String> save(@RequestBody DishDTO dishDTO){
        dishService.saveWithFlavor(dishDTO);
        return Result.success("新增成功");
    }

}
