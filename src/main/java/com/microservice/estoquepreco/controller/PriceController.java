package com.microservice.estoquepreco.controller;


import com.microservice.estoquepreco.constants.RabbitmqConstants;
import com.microservice.estoquepreco.dto.PriceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/price")
public class PriceController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Logger log = LoggerFactory.getLogger(PriceController.class);

    @PutMapping
    public ResponseEntity<?> changePrice (@RequestBody PriceDto priceDto){
        log.info("Sending price to queue");
        rabbitTemplate.convertAndSend(RabbitmqConstants.PRICE_QUEUE, priceDto);
        log.info("Price receive in queue");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
