package com.friends.accountservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.friends.accountservice.beans.Account;
import com.friends.accountservice.exceptions.AccountServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private KafkaTemplate<String, Object> template;

    @Value("${account-topic-name}")
    private String accountTopicName;

    @Autowired
    private ObjectMapper objectMapper;

    public boolean add(Account account) throws AccountServiceException {
        try {
            SendResult<String, Object> sendResult = template.send(accountTopicName, account.getId(),
                    objectMapper.writer().writeValueAsString(account)).get();
            System.out.println("Result : " + sendResult.getProducerRecord().toString());
            return true;
        } catch (Exception exception) {
            throw new AccountServiceException(exception.getMessage());
        }
    }
}
