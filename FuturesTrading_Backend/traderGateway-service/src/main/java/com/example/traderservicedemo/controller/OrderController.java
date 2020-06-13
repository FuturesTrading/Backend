package com.example.traderservicedemo.controller;

import com.example.common.exception.InvalidParameterException;
import com.example.common.response.ResultCode;
import com.example.common.util.DateUtil;
import com.example.traderservicedemo.core.processor.ProcessorFactory;
import com.example.traderservicedemo.model.Order;
import com.example.traderservicedemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.common.response.Result;
import com.example.common.util.ResultUtil;

import java.text.ParseException;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/trader/order")
    public Result createOrder(@RequestBody Order order,
                              @RequestParam(defaultValue = ProcessorFactory.NONE) String processStrategy,
                              @RequestParam(defaultValue = DateUtil.TOMMOROW_OPEN) String startTime,
                              @RequestParam(defaultValue = DateUtil.TOMMOROW_CLOSE) String endTime,
                              @RequestParam(defaultValue = "1") Integer slice,
                              @RequestParam(defaultValue = "5") Integer intervalMinute){
        if (order.getQuantity() <= 0)
            return ResultUtil.failure(ResultCode.WRONG_ORDER_QUANTITY);
        if (order.getProduct_id() == null)
            return ResultUtil.failure(ResultCode.WRONG_ORDER_PRODUCT);
        if (order.getVariety() >4 || order.getVariety()<=0)
            return ResultUtil.failure(ResultCode.WRONG_ORDER_VARIETY);

        ProcessorFactory.Parameter pp;

        Integer brokerId=order.getBroker_id();
        try {
            pp = new ProcessorFactory.Parameter(
                    processStrategy,
                    startTime,
                    endTime,
                    slice,
                    brokerId,
                    intervalMinute);
        }
        catch(ParseException e){
            return ResultUtil.failure(ResultCode.Failure_Create_ORDER);
        }

        try {
            orderService.createOrderWithStrategy(order,pp,startTime,endTime,slice,intervalMinute);
            return ResultUtil.success(order);
        }
        catch (InvalidParameterException | ParseException e){
            return ResultUtil.failure(ResultCode.Failure_Create_ORDER);
        }

    }


}
