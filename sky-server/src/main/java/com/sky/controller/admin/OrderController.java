package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lxq
 * @Date: 2024/3/24 1:28
 */
@RestController("adminOrderController")
@Slf4j
@Api(tags = "管理端-订单接口")
@RequestMapping("/admin/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PutMapping("cancel")
    @ApiOperation("取消订单")
    public Result cancelOrder(@PathVariable String reason,Long id) throws Exception {
        orderService.cancelOrder(id);
        return Result.success();
    }

    @GetMapping("statistics")
    @ApiOperation("各个状态的订单统计")
    public Result<OrderStatisticsVO> OrderStatus(){
        OrderStatisticsVO orderStatisticsVO = orderService.InquiryStatus();
        return Result.success(orderStatisticsVO);
    }

    @PutMapping("complete/{id}")
    @ApiOperation("完成订单")
    public Result Completed(@PathVariable Long id){
        orderService.CompleteOrder(id);
        return Result.success();
    }

    @PutMapping("rejection")
    @ApiOperation("拒单")
    public Result refuseOrder(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        orderService.refuseOrder(ordersRejectionDTO);
        return Result.success();
    }

    @PutMapping("/confirm")
    @ApiOperation("接单")
    public Result confirm(@RequestBody OrdersConfirmDTO ordersConfirmDTO) {
        orderService.confirm(ordersConfirmDTO);
        return Result.success();
    }

    @GetMapping("/details/{id}")
    @ApiOperation("查看订单详情")
    public Result OrderDetail(@PathVariable Long id){
        orderService.orderDetail(id);
        return Result.success();
    }

    @PutMapping("/delivery/{id}")
    @ApiOperation("派送订单")
    public Result delivery(@PathVariable("id") Long id) {
        orderService.delivery(id);
        return Result.success();
    }

    @GetMapping("conditionSearch")
    @ApiOperation("订单搜索")
    public Result<PageResult> orderSearch(OrdersPageQueryDTO ordersPageQueryDTO){
        PageResult pageResult = orderService.Search(ordersPageQueryDTO);
        return Result.success(pageResult);
    }
}

















