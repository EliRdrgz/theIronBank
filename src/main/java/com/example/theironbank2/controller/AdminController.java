package com.example.theironbank2.controller;

import com.example.theironbank2.dto.CheckingAccountDTO;

import com.example.theironbank2.model.CheckingAccount;
import com.example.theironbank2.security.requests.CreateAccountRequest;
import com.example.theironbank2.security.requests.CreateUserRequest;
import com.example.theironbank2.security.requests.ReadBalanceRequest;
import com.example.theironbank2.security.requests.TransferRequest;
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
    private String hello(Principal principal){
        return "Hello " + principal.getName()+ " wellcome back (:";
    }


    @PostMapping(value = "/create/user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest user) {
        Response createdResponse = kcAdminClient.createKeycloakUser(user);
        return ResponseEntity.status(createdResponse.getStatus()).build();

    }


    @PostMapping("/create/account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CheckingAccountDTO createAccount(@RequestBody CreateAccountRequest createAccountRequest ) {
        return adminService.createAccount(createAccountRequest);
    }
//    @PostMapping("/modify-balance")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public CheckingAccountDTO modifyBalance(@RequestBody CreateUserRequest.ModifyBalanceRequest modifyBalanceRequest ) {
//        return adminService.modifyBalance(modifyBalanceRequest);
//    }


    @PostMapping("/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Optional<CheckingAccount> checkBalance(@RequestBody ReadBalanceRequest readBalanceRequest ) {
        return adminService.checkBalance(readBalanceRequest);
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void makeTransfer(@RequestBody TransferRequest transferRequest ) {
        adminService.makeTransfer(transferRequest);
    }

}
