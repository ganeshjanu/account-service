package com.friends.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.friends.accountservice.bean.Account;
import com.friends.accountservice.bean.AccountTransaction;
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
public class AccountTransactionController {

    private static final Logger logger = LoggerFactory.getLogger(AccountTransactionController.class);

    @Autowired
    private KafkaTemplate<String, Object> template;

    @Value("${account-transaction-topic-name}")
    private String accountTransactionTopicName;

    @Autowired
    private ObjectMapper objectMapper ;

    @PostMapping("/accountTransaction")
    public boolean account(@RequestBody AccountTransaction accountTransaction) throws Exception {
        try {
            SendResult<String, Object> sendResult = template.send(accountTransactionTopicName, accountTransaction.getId(), objectMapper.writer().writeValueAsString(accountTransaction)).get();
            System.out.println("Result : " + sendResult.getProducerRecord().toString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }

}
