package com.example.theironbank2.controller;

import com.example.theironbank2.dto.CheckingAccountDTO;
import com.example.theironbank2.model.AccountHolder;
import com.example.theironbank2.model.CheckingAccount;
import com.example.theironbank2.security.config.KeycloakProvider;
import com.example.theironbank2.security.requests.LoginRequest;
import com.example.theironbank2.security.requests.ReadBalanceRequest;
import com.example.theironbank2.security.requests.ShowAccountsRequest;
import com.example.theironbank2.security.requests.TransferRequest;
import com.example.theironbank2.service.AccountHolderService;
import com.example.theironbank2.service.CheckingAccountService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    AccountHolderService accountHolderService;

    @Autowired
    CheckingAccountService checkingAccountService;

    private final KeycloakProvider kcProvider;

    public UserController(KeycloakProvider kcProvider) {
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

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void makeTransfer(@RequestBody TransferRequest transferRequest, Principal principal) {
        accountHolderService.makeTransfer(transferRequest, principal);
    }

    @GetMapping("/show-my-accounts")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CheckingAccountDTO> showMyAccounts(Principal principal) {
        return checkingAccountService.findAllByPrimaryOwner(principal);
    }

}
