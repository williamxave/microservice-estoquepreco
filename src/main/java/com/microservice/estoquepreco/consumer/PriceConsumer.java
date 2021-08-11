package com.microservice.estoquepreco.consumer;

import com.microservice.estoquepreco.constants.RabbitmqConstants;
import com.microservice.estoquepreco.dto.PriceDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PriceConsumer {

    @RabbitListener(queues = RabbitmqConstants.PRICE_QUEUE)
    public void priceConsumer(PriceDto priceDto){
        System.out.println(priceDto.toString());
    }
}
