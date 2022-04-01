package com.example.rabitrestapi.controller;

import com.example.rabitrestapi.service.TopicRabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("topicrabbit")
public class TopicRabbitController {

    @Autowired
    TopicRabbitService topicRabbitService;


    @PostMapping(value = "/createQueue")
    public String createQueue(
            @RequestParam(value = "routingKey") String routingKey,
            @RequestParam(value = "queueName") String queueName) {

        return topicRabbitService.createQueue(routingKey,queueName);
    }

    @PostMapping(value = "/sendTopicMessage")
    public String sendTopicMessage(
            @RequestParam(value = "routingKey") String routingKey,
            @RequestParam(value = "message") String message) {

        return topicRabbitService.sendTopicMessage(routingKey, message);
    }


}