package com.absa.assessment.messagebroker.config;



import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;


@EnableJms
@Configuration
public class ConfigureActiveMq {

    @Bean
    public Queue queue(){

        return new ActiveMQQueue("payment-message.queue");
    }
}
