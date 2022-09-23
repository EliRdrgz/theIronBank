package com.example.theironbank2.service;

import com.example.theironbank2.dto.*;
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

    TransferDTO makeTransfer(TransferRequest transferRequest);

    SavingsAccountDTO createSavingsAccount(CreateAccountRequest createAccountRequest);

    TransferDTO saveMoney(TransferRequest transferRequest);

    CreditAccountDTO createCreditAccount(CreateAccountRequest createAccountRequest);

}
