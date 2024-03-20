package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lxq
 * @Date: 2024/3/21 1:36
 */
@RestController("adminShopController")
@Slf4j
@RequestMapping("admin/shop")
@Api(tags = "店铺相关接口")
public class ShopController {

    public static final String KEY = "SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("{status}")
    @ApiOperation("设置店铺营业状态")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置营业状态为：{}",status==1?"营业中":"打样中");
        redisTemplate.opsForValue().set(KEY,status);
        return Result.success();
    }

    @GetMapping("status")
    @ApiOperation("获取店铺营业状态")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取营业状态为：{}",status==1?"营业中":"打样中");
        return Result.success(status);
    }
}















