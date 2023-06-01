package com.kushal.sns.sns.controller;

import com.kushal.sns.sns.dto.TransactionRequestDto;
import com.kushal.sns.sns.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/transactions")
    public void acceptTransaction(@RequestBody TransactionRequestDto transaction) {
        transactionService.processRequest(transaction);
    }


}
