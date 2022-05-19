//package com.blps.firstlaboratory.utils;
//
//import org.camunda.bpm.engine.delegate.DelegateExecution;
//import org.camunda.bpm.engine.delegate.JavaDelegate;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class Sender implements JavaDelegate {
//
//    private final JmsTemplate jmsTemplate;
//
//    @Value("${queueName}")
//    private String queue;
//
//    public Sender(JmsTemplate jmsTemplate) {
//        this.jmsTemplate = jmsTemplate;
//    }
//
//    public void send(String mail) {
//
//    }
//
//    @Override
//    public void execute(DelegateExecution delegateExecution) throws Exception {
//        try {
//            jmsTemplate.convertAndSend(queue, delegateExecution.getVariable("products"));
//        } catch (Exception e) {
//            System.err.println("Failed to send a message to" + mail);
//            e.printStackTrace();
//        }
//    }
//}