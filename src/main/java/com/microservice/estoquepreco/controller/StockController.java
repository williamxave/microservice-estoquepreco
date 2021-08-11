package com.microservice.estoquepreco.controller;


import com.microservice.estoquepreco.constants.RabbitmqConstants;
import com.microservice.estoquepreco.dto.StockDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Logger log = LoggerFactory.getLogger(StockController.class);

    @PutMapping
    public ResponseEntity<?> changeStock (@RequestBody StockDto stockDto){
        log.info("Sending stock to queue");
        rabbitTemplate.convertAndSend(RabbitmqConstants.STOCK_QUEUE, stockDto);
        log.info("Price receive in queue");
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
