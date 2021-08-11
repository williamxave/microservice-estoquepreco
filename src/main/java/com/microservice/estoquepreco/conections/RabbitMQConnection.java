package com.microservice.estoquepreco.conections;

import com.microservice.estoquepreco.constants.RabbitmqConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {

    private static final String NAME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    // AmqpAdmin interface responsável de conectar o rabbitmq e criar as filas
    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    //Criação da fila
    private Queue queue(String QueueName) {
        return new Queue(QueueName, true, false, false);
    }

    //Criação da exchange
    private DirectExchange exchange() {
        return new DirectExchange(NAME_EXCHANGE, true, false);
    }

    // Faz o binding entre a exchange e a fila
    private Binding relationship(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    //Utiliza os métodos acima para criar as filas no rabbitmq

    //Assim que a classe for construida ele vai executar o que está nessa método, e criar as filas
    @PostConstruct
    private void insert() {
        //Crias filas
        Queue stockQueue = this.queue(RabbitmqConstants.STOCK_QUEUE);
        Queue stockPrice = this.queue(RabbitmqConstants.PRICE_QUEUE);

        //Cria exchange
        DirectExchange trade = this.exchange();

        Binding stockLink = this.relationship(stockQueue, trade);
        Binding priceLink = this.relationship(stockPrice, trade);

        //Criando filas no rabbitmq
        this.amqpAdmin.declareQueue(stockQueue);
        this.amqpAdmin.declareQueue(stockPrice);

        //Cria a exchange, como essa é uma padrão que já existe lá, ele faz a verificação e como já existe usa aquela
        this.amqpAdmin.declareExchange(trade);

        //Cria os binding
        this.amqpAdmin.declareBinding(stockLink);
        this.amqpAdmin.declareBinding(priceLink);

    }

}
