package com.friends.accountserviceproducer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.friends.accountserviceproducer.beans.AccountTransaction;
import com.friends.accountserviceproducer.services.AccountService;
import com.friends.accountserviceproducer.services.AccountTransactionService;
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
    private AccountTransactionService accountTransactionService;

    @PostMapping("/accountTransaction")
    public boolean add(@RequestBody AccountTransaction accountTransaction) throws Exception {
      return accountTransactionService.add(accountTransaction);
    }

}
