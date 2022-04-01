package com.example.rabitrestapi.rabbit;

import com.example.rabitrestapi.configuration.model.RabbitMqConfigModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class RabbitMqSender {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqConfigModel rabbitMqConfigModel;

    public RabbitMqSender(RabbitTemplate rabbitTemplate, RabbitMqConfigModel rabbitMqConfigModel) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqConfigModel = rabbitMqConfigModel;
    }

    public void sendToRabbitWithRoutingKey(String routingKey,String message) throws UnsupportedEncodingException {
        log.info("sending message to routingKey with name : {} and message : {}",routingKey,message);

        this.rabbitTemplate.convertAndSend(this.rabbitMqConfigModel.getExchange(),routingKey,message);
        //this.rabbitTemplate.send(this.rabbitMqConfigModel.getExchange(), routingKey, new Message(message.getBytes("UTF-8"),null));
        log.info("message sent to rabbit queue");
    }

}
