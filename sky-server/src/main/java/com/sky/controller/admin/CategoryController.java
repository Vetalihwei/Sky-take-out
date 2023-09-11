package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "套餐管理")
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Operation(summary = "修改分类")
    @PutMapping
    public Result<String> update(@RequestBody  CategoryDTO categoryDTO){
        log.info("分类信息：{}",categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success("修改成功");
    }

    @Operation(summary = "根据id删除分类")
    @DeleteMapping
    public Result<String> delete(Long id){
        categoryService.delete(id);
        return Result.success("删除成功");
    }


    @Operation(summary = "启用禁用分类")
    @PostMapping("/status/{status}")
    public Result<String> openOrstop(@PathVariable  Integer status,Long id){
        categoryService.openOrstop(status,id);
        return Result.success("操作成功");
    }

    @Operation(summary = "根据类型查询分类")
    @GetMapping("/list")
    public Result<List<Category>> list(Integer type){
        List<Category> list=categoryService.list(type);
        return Result.success(list);
    }

    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(@ParameterObject  CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分页信息：{}",categoryPageQueryDTO);
        PageResult pageResult=categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @Operation(summary = "新增分类")
    @PostMapping
    public Result<String> save(@RequestBody  CategoryDTO categoryDTO){
        log.info("分类信息：{}",categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success("新增成功");
    }

}
