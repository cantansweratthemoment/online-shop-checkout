package com.blps.firstlaboratory.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Sender {

    private final JmsTemplate jmsTemplate;

    @Value("${queueName}")
    private String queue;

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(String mail) {
        try {
            jmsTemplate.convertAndSend(queue, mail);
        } catch (Exception e) {
            System.err.println("Failed to send a message to" + mail);
            e.printStackTrace();
        }
    }
}