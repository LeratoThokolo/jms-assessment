package com.absa.assessment.messagebroker.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.assessment.absa.message.Message;

import javax.jms.Queue;



@RequestMapping("/api/publish")
@RestController
public class ProducerController {


    @Autowired
    private JmsTemplate jmsTemplate;


    @Autowired
    private Queue queue;


    @PostMapping("/message")
    public Message publishMessage(@RequestBody Message message){

        if(message.getMessageId() == 0 && message.getMessageBody().length() == 0){

            throw new RuntimeException("Message cannot be empty");
        }


        this.jmsTemplate.convertAndSend(queue, message.getMessageBody());

        return message;
    }
}
