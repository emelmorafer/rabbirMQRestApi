package com.example.rabitrestapi.service;


import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;

import java.util.Map;

public interface TopicRabbitService {

    String createQueue(String routingKey,String queueName);

    String sendTopicMessage (String routingKey, String message);

}
