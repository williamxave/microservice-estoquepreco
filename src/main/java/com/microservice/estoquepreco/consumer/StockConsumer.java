package com.microservice.estoquepreco.consumer;

import com.microservice.estoquepreco.constants.RabbitmqConstants;
import com.microservice.estoquepreco.dto.StockDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class StockConsumer{

    @RabbitListener(queues = RabbitmqConstants.STOCK_QUEUE)
    public void consumer(StockDto stockDto){
        System.out.println(stockDto.toString());
    }
}
