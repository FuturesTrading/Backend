package com.example.tradergateway.service;


import com.example.tradergateway.core.processor.Processor;
import com.example.tradergateway.core.processor.ProcessorFactory;
import com.example.tradergateway.core.sendMessage.SendMessage;
import com.example.tradergateway.core.sendMessage.SendMessageInstant;
import com.example.tradergateway.dto.OrdersDTO;
import com.example.tradergateway.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

import static com.example.tradergateway.core.processor.ProcessorFactory.TWAP;

@Service
public class OrdersDTOService {
    @Autowired
    private ProcessorFactory processorFactory;

    @Autowired
    private BrokerClient brokerClient;

    @Autowired
    private SendMessage sendMessage;

    @Autowired
    private SendMessageInstant sendMessageInstant;

    @Autowired
    private ProductService productService;

    public Boolean createOrderWithStrategy(OrdersDTO order, ProcessorFactory.Parameter pp, String startTime, String endTime,Integer intervalMinute) throws ParseException {
//        Product product=new Product();
//        String name=order.getProductName();
//        String period=order.getPeriod();
//        if(period==null || name==null) return false;
//        product.setPeriod(period);
//        product.setProductName(name);
//        Integer id=productService.addProduct(product);
//        order.setProductId(id);
        Processor processor = processorFactory.create(pp);
        List<OrdersDTO> orders = processor.process(order);
        System.out.println(orders.toString());
        if(pp.getStrategy().equals(TWAP))
        sendMessage.productionDelayMessage(orders,startTime,intervalMinute);
        else{
            sendMessageInstant.productionInstantMessage(orders);
        }
        return true;
    }

    public List<OrdersDTO> findOrderByTraderId(Integer id) {
        return brokerClient.getOrdersByTraderId(id);
    }
}
