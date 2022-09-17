package com.example.theironbank2.service;

import com.example.theironbank2.dto.CheckingAccountDTO;
import com.example.theironbank2.model.CheckingAccount;
import com.example.theironbank2.security.requests.CreateAccountRequest;
import com.example.theironbank2.security.requests.ReadBalanceRequest;
import com.example.theironbank2.security.requests.TransferRequest;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Optional;


public interface AdminService {

    CheckingAccountDTO createAccount(CreateAccountRequest createAccountRequest);

    Optional<CheckingAccount> checkBalance(ReadBalanceRequest readBalanceRequest);

    void makeTransfer(TransferRequest transferRequest);

//    CheckingAccountDTO makeTransfer(ReadBalanceRequest readBalanceRequest, Principal principal);




//    CheckingAccountDTO modifyBalance(CreateUserRequest.ModifyBalanceRequest modifyBalanceRequest);
}
