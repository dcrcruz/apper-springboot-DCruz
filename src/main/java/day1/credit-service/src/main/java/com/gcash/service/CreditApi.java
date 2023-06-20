package com.gcash.service;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreditApi {

    private final CreditService creditService;

    public CreditApi(CreditService creditService) {
        this.creditService = creditService;
    }

    //create account
    public Account createNewAccount (Double initialBalance) {
        return creditService.createAccount(initialBalance);
    }

    public List<Account> getAllAccounts() {
        return creditService.getAllAccounts();
    }
}
