package com.absa.assessment.messagebroker.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import za.co.assessment.absa.message.Message;

import javax.jms.Queue;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ProducerController.class, secure = false)
public class ProducerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProducerController producerController;

    @MockBean
    private Queue queue;

    @MockBean
    private JmsTemplate jmsTemplate;


    @Test
    public void publishMessage() throws Exception {

        Message message = new Message();
        message.setMessageId(1);
        message.setMessageBody("This is the payment message");

        Mockito.when(this.producerController.publishMessage(Mockito.any(Message.class))).thenReturn(message);



        String dataToPost = new ObjectMapper().writeValueAsString(message);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/publish/message").accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(String.valueOf(dataToPost))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();


       assertEquals(message, result.getResponse().getContentAsString());
    }
}