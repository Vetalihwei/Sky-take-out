package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController("adminSetmealController")
@Slf4j
@Tag(name= "套餐管理")
@RequestMapping("/admin/setmeal")
public class SetmealController {

    @Autowired
    SetmealService setmealService;

    @Operation(summary = "根据id查询")
    @GetMapping("/{id}")
    public Result<SetmealVO> getByIdWithDish(@PathVariable Long id){
        SetmealVO setmealVO=setmealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    @Operation(summary = "修改套餐")
    @PutMapping
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result<String> update(@RequestBody SetmealDTO setmealDTO){
        setmealService.updateWithSetmealDish(setmealDTO);
        return Result.success("修改成功");
    }

    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    @Operation(summary = "套餐停售起售")
    @PostMapping("/status/{status}")
    public Result<String> openOrstop(@PathVariable Integer status,Long id){
        setmealService.openOrstop(status,id);
        return Result.success("操作成功");
    }

    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    @Operation(summary = "批量删除套餐")
    @DeleteMapping
    public Result<String> deleteBatch(Long[] ids){
        setmealService.deleteBatch(ids);
        return Result.success("删除成功");
    }


    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(@ParameterObject SetmealPageQueryDTO setmealPageQueryDTO){
        PageResult pageResult=setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }


    @Operation(summary = "新增套餐")
    @PostMapping
    public Result<String> save(@RequestBody SetmealDTO setmealDTO){
        setmealService.saveWithDish(setmealDTO);
        return Result.success("新增成功");
    }


}
