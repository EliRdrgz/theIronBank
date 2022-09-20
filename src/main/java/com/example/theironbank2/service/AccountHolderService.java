package com.example.theironbank2.service;

import com.example.theironbank2.dto.AccountHolderDTO;
import com.example.theironbank2.dto.CheckingAccountDTO;
import com.example.theironbank2.model.CheckingAccount;
import com.example.theironbank2.repository.AccountHolderRepository;
import com.example.theironbank2.security.requests.ReadBalanceRequest;
import com.example.theironbank2.security.requests.ShowAccountsRequest;
import com.example.theironbank2.security.requests.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Optional;

public interface AccountHolderService {

    AccountHolderDTO create(AccountHolderDTO accountHolderDTO);


    Optional<CheckingAccount> checkMyBalance(ReadBalanceRequest readBalanceRequest, Principal principal);

    void makeTransfer(TransferRequest transferRequest, Principal principal);


}
