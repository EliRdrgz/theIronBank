package com.example.theironbank2.service;

import com.example.theironbank2.dto.AccountHolderDTO;
import com.example.theironbank2.dto.TransferDTO;
import com.example.theironbank2.model.CheckingAccount;
import com.example.theironbank2.security.requests.ReadBalanceRequest;
import com.example.theironbank2.security.requests.TransferRequest;

import java.security.Principal;
import java.util.Optional;

public interface AccountHolderService {

    AccountHolderDTO create(AccountHolderDTO accountHolderDTO);


    Optional<CheckingAccount> checkMyBalance(ReadBalanceRequest readBalanceRequest, Principal principal);

    void makeTransfer(TransferRequest transferRequest, Principal principal);


    TransferDTO saveMoney(TransferRequest transferRequest, Principal principal);

    TransferDTO withdrawMoney(TransferRequest transferRequest, Principal principal);
}
