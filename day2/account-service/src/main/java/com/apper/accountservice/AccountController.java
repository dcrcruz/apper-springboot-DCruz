package com.apper.accountservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        Account account = accountService.create(
            request.getFirstName(),
            request.getLastName(),
            request.getEmail(),
            request.getPassword()
        );
        // set verification code
        CreateAccountResponse response = new CreateAccountResponse();
        response.setVerificationCode(account.getVerificationCode());

        //return verification code
        return response;
    }

    @GetMapping("{accountId}")
    public GetAccountResponse getAccount(@PathVariable String accountId) {
        Account account = accountService.get(accountId);

        return createGetAccountResponse(account);
    }

    @GetMapping
    public List<GetAccountResponse> getAllAccounts() {
        List<GetAccountResponse> responseList = new ArrayList<>();

        for (Account account : accountService.getAll()) {
            GetAccountResponse response = createGetAccountResponse(account);
            responseList.add(response);
        }

        // list all accounts
        return responseList;
    }

    // for cleaner code,
    private GetAccountResponse createGetAccountResponse(Account account) {
        GetAccountResponse response = new GetAccountResponse();
        response.setBalance(account.getBalance());
        response.setFirstName(account.getFirstName());
        response.setLastName(account.getLastName());
        response.setUsername(account.getUsername());
        response.setRegistrationDate(account.getCreationDate());
        response.setAccountId(account.getId());

        // return all variables
        return response;
    }

    // Lab 1: Update Account
    @PutMapping("{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public PutAccountResponse updateAccount(@PathVariable String accountId, @RequestBody CreateAccountRequest request) {
        Account account = accountService.get(accountId);

        accountService.update(
            accountId,
            request.getFirstName(),
            request.getLastName(),
            request.getEmail(),
            request.getPassword()
        );

        // Postman response if update is successful
        PutAccountResponse response = new PutAccountResponse();
        response.setLastUpdate(LocalDateTime.now());

        return response;
    }

    // Lab 1: Delete Account
    @DeleteMapping("{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable String accountId) {
        accountService.delete(accountId);
    }

}
