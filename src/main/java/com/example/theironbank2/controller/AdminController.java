package com.example.theironbank2.controller;

import com.example.theironbank2.dto.*;

import com.example.theironbank2.model.CheckingAccount;
import com.example.theironbank2.security.requests.*;
import com.example.theironbank2.security.service.KeycloakAdminClientService;
import com.example.theironbank2.service.AdminService;
import com.example.theironbank2.service.CheckingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    CheckingAccountService checkingAccountService;

    @Autowired
    AdminService adminService;

    @Autowired
    KeycloakAdminClientService kcAdminClient;


    @GetMapping("/hello")
    private String hello(){
        return "<h1> Wellcome to IRONBANK 2.0</h1>";
    }


    @PostMapping(value = "/create/user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest user) {
        Response createdResponse = kcAdminClient.createKeycloakUser(user);
        return ResponseEntity.status(createdResponse.getStatus()).build();

    }


    @PostMapping("/create/checking-account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CheckingAccountDTO createAccount(@RequestBody CreateAccountRequest createAccountRequest ) {
        return adminService.createAccount(createAccountRequest);
    }

    @PostMapping("/create/savings-account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SavingsAccountDTO createSavingsAccount(@RequestBody CreateAccountRequest createAccountRequest ) {
        return adminService.createSavingsAccount(createAccountRequest);
    }

    @PostMapping("/create/credit-account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreditAccountDTO createCreditAccount(@RequestBody CreateAccountRequest createAccountRequest ) {
        return adminService.createCreditAccount(createAccountRequest);
    }



    @GetMapping("/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Optional<CheckingAccount> checkBalance(@RequestBody ReadBalanceRequest readBalanceRequest ) {
        return adminService.checkBalance(readBalanceRequest);
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransferDTO makeTransfer(@RequestBody TransferRequest transferRequest ) {
       return adminService.makeTransfer(transferRequest);
    }

    @PostMapping("/save-money")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransferDTO saveMoney(@RequestBody TransferRequest transferRequest ) {
       return adminService.saveMoney(transferRequest);
    }

}
