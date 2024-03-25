package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import orderService.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;


/**
 * @Author: lxq
 * @Date: 2024/3/24 23:32
 */

@RestController
@RequestMapping("/admin/report")
@Api(tags = "数据统计接口")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("turnoverStatistics")
    @ApiOperation("营业额统计")
    public Result<TurnoverReportVO> turnoverStatics(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        return Result.success(reportService.getTurnoverStatic(begin,end));
    }

    @GetMapping("/userStatistics")
    @ApiOperation("用户数据统计")
    public Result<UserReportVO> userStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){

        return Result.success(reportService.getUserStatistics(begin,end));
    }

    /**
     * 获取订单统计数据
     *
     * @param begin 统计开始日期，格式为"yyyy-MM-dd"
     * @param end 统计结束日期，格式为"yyyy-MM-dd"
     * @return 返回订单统计结果，包含在Result<OrderReportVO>中，其中OrderReportVO是订单报告的视图对象
     */
    @GetMapping("/ordersStatistics")
    @ApiOperation("用户数据统计")
    public Result<OrderReportVO> orderStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate end){

        // 调用reportService中的方法，获取指定日期范围内的订单统计数据
        return Result.success(reportService.getOrderStatistics(begin,end));
    }

    @GetMapping("/top10")
    @ApiOperation("销量排名统计")
    public Result<SalesTop10ReportVO> top10(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        return Result.success(reportService.getSalesTop10(begin,end));
    }
    @GetMapping("/export")
    @ApiOperation("导出运营数据报表")
    public void export(HttpServletResponse response){
        reportService.exportBusinessData(response);
    }
}
