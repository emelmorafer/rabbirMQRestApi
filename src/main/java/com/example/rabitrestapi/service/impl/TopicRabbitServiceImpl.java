package com.example.rabitrestapi.service.impl;


import com.example.rabitrestapi.configuration.RabbitMqConfig;
import com.example.rabitrestapi.configuration.model.RabbitMqConfigModel;
import com.example.rabitrestapi.rabbit.RabbitMqSender;
import com.example.rabitrestapi.service.TopicRabbitService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TopicRabbitServiceImpl implements TopicRabbitService {
    private final RabbitAdmin rabbitAdmin;
    private final RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;
    private final RabbitMqConfig rabbitMqConfig;
    private final RabbitMqSender rabbitMqSender;

    public TopicRabbitServiceImpl(
            RabbitAdmin rabbitAdmin,
            RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry,
            RabbitMqConfig rabbitMqConfig,
            RabbitMqSender rabbitMqSender) {
        this.rabbitAdmin = rabbitAdmin;
        this.rabbitListenerEndpointRegistry = rabbitListenerEndpointRegistry;
        this.rabbitMqConfig = rabbitMqConfig;
        this.rabbitMqSender = rabbitMqSender;
    }

    private static final Logger LOGGER = LogManager.getLogger(TopicRabbitServiceImpl.class);


    public String createQueue(String routingKey,String queueName) {
        try {
            Queue queue = new Queue(queueName, true, false, false);
            Binding binding = new Binding(
                    queueName,
                    Binding.DestinationType.QUEUE,
                    rabbitMqConfig.defaultExchange().getName(),
                    routingKey,
                    null
            );

            rabbitAdmin.declareQueue(queue);
            rabbitAdmin.declareBinding(binding);

            return " a new Queue was created with name '" + queueName + "'";
        } catch (Exception e) {
            return "Something went wrong:" + e.getMessage();
        }
    }

    public String sendTopicMessage(String routingKey, String message) {
        try {
            rabbitMqSender.sendToRabbitWithRoutingKey(routingKey,message);
            return " a new Message was send '" + message + "' with routingKey '" + routingKey + "'";
        } catch (Exception e) {
            return "Something went wrong:" + e.getMessage();
        }
    }

}
