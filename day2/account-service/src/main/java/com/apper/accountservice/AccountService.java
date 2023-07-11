package com.apper.accountservice;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private List<Account> accounts = new ArrayList<>();

    public Account create(String firstName, String lastName, String username, String clearPassword) {
        Account account = new Account();
        // Lab 1: Generate ID
        String id = IdGeneratorService.getNextId();
        System.out.println("Generated id: " + id); // print ID in terminal

        account.setId(id);
        account.setBalance(1_000.0); // set initial balance

        LocalDateTime now = LocalDateTime.now();
        account.setCreationDate(now);
        account.setLastUpdated(now);

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setClearPassword(clearPassword);
        // Lab 1: Set verification code length to 6
        account.setVerificationCode(IdGeneratorService.generateRandomCharacters(6));

        accounts.add(account);

        return account;
    }

    public Account get(String accountId) {
        for (Account account : accounts) {
            if(account.getId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public List<Account> getAll() {
        return accounts;
    }

    // Lab 1: Update account
    public void update(String accountId, String firstName, String lastName, String userName, String password) {
        for (Account account : accounts) {
            if (account.getId().equals(accountId)) {
                account.setFirstName(firstName);
                account.setLastName(lastName);
                account.setUsername(userName);
                account.setClearPassword(password);
                account.setLastUpdated(LocalDateTime.now());
                break;
            }
        }
    }

    // Lab 1: Delete account
    public void delete(String accountId) {
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getId().equals(accountId)) {
                accounts.remove(i);
                break;
            }
        }
    }
}
