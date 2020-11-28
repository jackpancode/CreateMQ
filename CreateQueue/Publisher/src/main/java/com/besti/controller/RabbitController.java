package com.besti.controller;

import com.besti.Service.Rabbitmqcrate;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jack Pan
 * @version 1.0 2020/11/28
 */
@RestController
public class RabbitController {

    @Autowired
    Rabbitmqcrate rabbitmqcrate;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AmqpTemplate amqpTemplate;

    @PostMapping(value = "/create")
    public void Create(@RequestParam("queueName") String queueName,@RequestParam("exchangeName") String exchangeName,@RequestParam("routekey") String routekey ) {

        rabbitmqcrate
                .registerExchangeAndQueueAndBinding(queueName, routekey, exchangeName, ExchangeTypes.TOPIC);

        MultiValueMap<String, Object> hashMap = new LinkedMultiValueMap<>();
        hashMap.add("queueName", queueName);

        String url = "http://localhost:7002/add";
        String result = restTemplate.postForObject(url, hashMap, String.class);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        amqpTemplate.convertAndSend(exchangeName,routekey,"hello-Rabbitmq");

    }
}
