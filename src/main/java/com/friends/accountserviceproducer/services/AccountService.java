package com.friends.accountserviceproducer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.friends.accountserviceproducer.beans.Account;
import com.friends.accountserviceproducer.exceptions.AccountServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

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
            LOGGER.info("Account msg sent to topic : " + sendResult.getProducerRecord().toString());
            return true;
        } catch (Exception exception) {
            throw new AccountServiceException(exception.getMessage());
        }
    }

}
