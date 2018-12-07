package com.absa.assessment.messagebroker.consumer;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {


    @JmsListener(destination = "payment-message.queue")
    public void messageConsumer(String message){

        System.out.println("Message received : " + message);
    }
}
