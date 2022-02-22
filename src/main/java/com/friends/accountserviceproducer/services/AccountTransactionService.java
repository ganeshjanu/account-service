package com.friends.accountserviceproducer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.friends.accountserviceproducer.beans.AccountTransaction;
import com.friends.accountserviceproducer.exceptions.AccountServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class AccountTransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountTransactionService.class);

    @Autowired
    private KafkaTemplate<String, Object> template;

    @Value("${account-transaction-topic-name}")
    private String accountTransactionTopicName;

    @Autowired
    private ObjectMapper objectMapper;

    public boolean add(AccountTransaction accountTransaction) throws AccountServiceException {
        try {
            SendResult<String, Object> sendResult = template.send(accountTransactionTopicName, accountTransaction.getTransactionId(),
                    objectMapper.writer().writeValueAsString(accountTransaction)).get();
            LOGGER.info("Account msg sent to topic : " + sendResult.getProducerRecord().toString());
            return true;
        } catch (Exception exception) {
            throw new AccountServiceException(exception.getMessage());
        }
    }
}
