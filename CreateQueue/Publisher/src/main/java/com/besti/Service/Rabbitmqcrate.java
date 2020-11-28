package com.besti.Service;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jack Pan
 * @version 1.0 2020/11/27
 */
@Component
public class Rabbitmqcrate {

    @Autowired
    AmqpAdmin amqpAdmin;

    private CustomExchange customExchange;
    private Queue queue;
    private Binding binding;

    public void registerExchangeAndQueueAndBinding(String queueName, String routingKey, String exchangeName, String exchangeType) {
        this.setCustomExchange(new CustomExchange(exchangeName,exchangeType,true,true));
        this.setQueue(new Queue(queueName,true));
        this.setBinding(
                new Binding(
                        this.getQueue().getName(),
                        Binding.DestinationType.QUEUE,
                        this.getCustomExchange().getName(),
                        routingKey,
                        null
                )
        );
        amqpAdmin.declareExchange(this.getCustomExchange());
        amqpAdmin.declareQueue(this.getQueue());
        amqpAdmin.declareBinding(this.getBinding());
    }

    private CustomExchange getCustomExchange() {
        return customExchange;
    }

    private void setCustomExchange(CustomExchange customExchange) {
        this.customExchange = customExchange;
    }

    private Queue getQueue() {
        return queue;
    }

    private void setQueue(Queue queue) {
        this.queue = queue;
    }

    private Binding getBinding() {
        return binding;
    }

    private void setBinding(Binding binding) {
        this.binding = binding;
    }

}
