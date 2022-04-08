package com.example.rabitrestapi.service;


import java.util.Map;

public interface TopicRabbitService {

    String createQueue(String routingKey,String queueName);

    String createHeaderQueue(String queueName);

    String sendTopicMessage (String routingKey, String message);

    String sendHeaderMessage(String message, Map<String, Object> properties);

}
