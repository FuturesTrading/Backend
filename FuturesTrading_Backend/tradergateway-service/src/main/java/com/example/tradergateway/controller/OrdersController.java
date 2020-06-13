package com.example.tradergateway.controller;


import com.example.demo.exception.InvalidParameterException;
import com.example.demo.response.Result;
import com.example.demo.response.ResultCode;
import com.example.demo.util.DateUtil;
import com.example.demo.util.ResultUtil;
import com.example.tradergateway.core.processor.ProcessorFactory;
import com.example.tradergateway.dto.OrdersDTO;
import com.example.tradergateway.service.OrdersDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/TraderGateway")
public class OrdersController {

    @Autowired
    private OrdersDTOService ordersDTOService;

    @PostMapping(value = "/order")
    public Result createOrder(@RequestBody OrdersDTO orderDTO,
                              @RequestParam(defaultValue = ProcessorFactory.NONE) String processStrategy,
                              @RequestParam(defaultValue = DateUtil.TOMMOROW_OPEN) String startTime,
                              @RequestParam(defaultValue = DateUtil.TOMMOROW_CLOSE) String endTime,
                              @RequestParam(defaultValue = "1") Integer slice,
                              @RequestParam(defaultValue = "5") Integer intervalMinute){
        if (orderDTO.getQuantity() <= 0)
            return ResultUtil.failure(ResultCode.WRONG_ORDER_QUANTITY);
        if (orderDTO.getProductId() == null)
            return ResultUtil.failure(ResultCode.WRONG_ORDER_PRODUCT);
        if (orderDTO.getVariety() >4 || orderDTO.getVariety()<=0)
            return ResultUtil.failure(ResultCode.WRONG_ORDER_VARIETY);

        ProcessorFactory.Parameter pp;

        Integer brokerId=orderDTO.getBrokerId();
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
            ordersDTOService.createOrderWithStrategy(orderDTO,pp,startTime,endTime,slice,intervalMinute);
            return ResultUtil.success(orderDTO);
        }
        catch (InvalidParameterException | ParseException e){
            return ResultUtil.failure(ResultCode.Failure_Create_ORDER);
        }
    }

    @GetMapping(value = "/order/traderId")
    public Result<List<OrdersDTO>> findOrderByTraderId(@RequestParam(name = "traderId") Integer id){
        return ResultUtil.success(ordersDTOService.findOrderByTraderId(id));
    }
}
