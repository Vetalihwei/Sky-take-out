package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@Slf4j
@RequestMapping("/admin/shop")
@Tag(name = "店铺相关接口")
public class ShopController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Operation(summary = "设置店铺营业状态")
    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status){
        redisTemplate.opsForValue().set("shop_status",status);
        return Result.success();
    }


    @GetMapping("/status")
    public Result<Integer> getstatus(){
        return Result.success((Integer)redisTemplate.opsForValue().get("shop_status"));
    }
}
