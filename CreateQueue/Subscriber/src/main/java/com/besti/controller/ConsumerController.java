package com.besti.controller;

import com.besti.util.SpringUtil;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jack Pan
 * @version 1.0 2020/11/28
 */
@RestController
public class ConsumerController {

    @PostMapping(value = "/add")
    public void addNewListener(@RequestParam("queueName") String queueName){
        SimpleMessageListenerContainer container = SpringUtil.getBean(SimpleMessageListenerContainer.class);
        container.addQueueNames(queueName);
    }
}
