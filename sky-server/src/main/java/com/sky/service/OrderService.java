package com.sky.service;

import com.github.pagehelper.Page;
import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

/**
 * @Author: lxq
 * @Date: 2024/3/23 16:55
 */
public interface OrderService {

    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    PageResult pageQuery4User(int page, int pageSize, Integer status);

    void cancelOrder(Long id) throws Exception;

    OrderVO orderDetail(Long id);

    void OneMore(Long id);

    OrderStatisticsVO InquiryStatus();

    void CompleteOrder(Long id);

    void refuseOrder(OrdersRejectionDTO ordersRejectionDTO) throws Exception;

    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    void delivery(Long id);

    PageResult Search(OrdersPageQueryDTO ordersPageQueryDTO);

    void reminder(Long id);
}
