package com.friends.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.friends.accountservice.bean.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountServiceController {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceController.class);

    @Autowired
    private KafkaTemplate<String, Object> template;

    @Value("${topic-name}")
    private String topicName;

   private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/account")
    public boolean account(@RequestBody Account account) throws Exception {
        try {
           SendResult<String, Object> sendResult = template.send(topicName, account.getId(), objectMapper.writer().writeValueAsString(account)).get();
           System.out.println("Result : " + sendResult.getProducerRecord().toString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }


}
