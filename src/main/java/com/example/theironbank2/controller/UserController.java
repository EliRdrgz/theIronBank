package com.example.theironbank2.controller;

import com.example.theironbank2.dto.CheckingAccountDTO;
import com.example.theironbank2.dto.CreditAccountDTO;
import com.example.theironbank2.dto.SavingsAccountDTO;
import com.example.theironbank2.dto.TransferDTO;

import com.example.theironbank2.model.CheckingAccount;

import com.example.theironbank2.security.config.KeycloakProvider;
import com.example.theironbank2.security.requests.LoginRequest;
import com.example.theironbank2.security.requests.ReadBalanceRequest;

import com.example.theironbank2.security.requests.TransferRequest;
import com.example.theironbank2.service.AccountHolderService;
import com.example.theironbank2.service.CheckingAccountService;
import com.example.theironbank2.service.CreditAccountService;
import com.example.theironbank2.service.SavingsAccountService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class UserController {



    private final AccountHolderService accountHolderService;


    private final CheckingAccountService checkingAccountService;


    private final SavingsAccountService savingsAccountService;


    private final CreditAccountService creditAccountService;

    private final KeycloakProvider kcProvider;

    public UserController(AccountHolderService accountHolderService, CheckingAccountService checkingAccountService, SavingsAccountService savingsAccountService, CreditAccountService creditAccountService, KeycloakProvider kcProvider) {
        this.accountHolderService = accountHolderService;
        this.checkingAccountService = checkingAccountService;
        this.savingsAccountService = savingsAccountService;
        this.creditAccountService = creditAccountService;
        this.kcProvider = kcProvider;
    }

    @PostMapping("/get-token")
    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody LoginRequest loginRequest) {
        Keycloak keycloak = kcProvider.newKeycloakBuilderWithPasswordCredentials(loginRequest.getUsername(), loginRequest.getPassword()).build();

        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }
    }


    @GetMapping("/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Optional<CheckingAccount> checkMyBalance(@RequestBody ReadBalanceRequest readBalanceRequest, Principal principal) {
        return accountHolderService.checkMyBalance(readBalanceRequest, principal);
    }

    // no muestra nada en pantalla...
    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void makeTransfer(@RequestBody TransferRequest transferRequest, Principal principal) {
        accountHolderService.makeTransfer(transferRequest, principal);
    }



    @GetMapping("/show-my-checkingaccounts")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CheckingAccountDTO> showMyAccounts(Principal principal) {
        return checkingAccountService.findAllByPrimaryOwner(principal);
    }


    @GetMapping("/show-my-savingsaccounts")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<SavingsAccountDTO> showMySavingsAccounts(Principal principal) {
        return savingsAccountService.findAllByPrimaryOwner(principal);
    }

     @GetMapping("/show-my-creditsaccounts")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CreditAccountDTO> showMyCreditAccounts(Principal principal) {
        return creditAccountService.findAllByPrimaryOwner(principal);
    }


    @PostMapping("/save-money")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransferDTO saveMoney(@RequestBody TransferRequest transferRequest, Principal principal) {
       return accountHolderService.saveMoney(transferRequest, principal);
    }

    @PostMapping("/withdraw-money")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransferDTO withdrawMoney(@RequestBody TransferRequest transferRequest, Principal principal) {
        return accountHolderService.withdrawMoney(transferRequest, principal);
    }

}
