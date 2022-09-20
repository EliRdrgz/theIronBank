package com.example.theironbank2.service;

import com.example.theironbank2.dto.AccountHolderDTO;
import com.example.theironbank2.dto.CheckingAccountDTO;
import com.example.theironbank2.model.AccountHolder;
import com.example.theironbank2.model.CheckingAccount;
import com.example.theironbank2.repository.AccountHolderRepository;
import com.example.theironbank2.repository.CheckingAccountRepository;
import com.example.theironbank2.security.requests.ReadBalanceRequest;
import com.example.theironbank2.security.requests.ShowAccountsRequest;
import com.example.theironbank2.security.requests.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    CheckingAccountService checkingAccountService;

    @Override
    public AccountHolderDTO create(AccountHolderDTO accountHolderDTO) {
        var entity = new AccountHolder();
        entity.setKeycloakId(accountHolderDTO.getKeycloakId());
        entity.setName(accountHolderDTO.getName());
        var storedAccountHolder = accountHolderRepository.save(entity);
        var dtoToReturn = new AccountHolderDTO();
        dtoToReturn.setKeycloakId(storedAccountHolder.getKeycloakId());
        dtoToReturn.setName(storedAccountHolder.getName());
        dtoToReturn.setId(storedAccountHolder.getId());
        return dtoToReturn;
    }

//I want to create a method to check my own balance but no of the others users.
    @Override
    public Optional<CheckingAccount> checkMyBalance(ReadBalanceRequest readBalanceRequest, Principal principal) {
        var accountId = readBalanceRequest.getAccountId();
        var holderId = readBalanceRequest.getAccountHolderId();
        var accountKeycloakId = accountHolderRepository.findById(holderId).get().getKeycloakId();
        var principalKeycloakId = principal.getName();
        Optional<AccountHolder> owner = accountHolderRepository.findById(holderId);
        Optional<CheckingAccount> account = checkingAccountRepository.findById(accountId);
        if (owner.isEmpty()) {
            throw new RuntimeException("Owner not found. Please try again or create a new owner.");
        } else if (account.isEmpty()) {
            throw new RuntimeException("Account not found. Please try again or create a new account.");
        } else if (account.get().getPrimaryOwner().getId() != holderId) {
            throw new RuntimeException("You are not the owner of this account. Please try again or create a new account.");
        } else {
            validateIsOwner(principal, accountKeycloakId);
            return account;
        }
    }


    @Override
    public void makeTransfer(TransferRequest transferRequest, Principal principal) {
        var fromAccount = checkingAccountRepository.findById(transferRequest.getOriginAccount()).get();
        var toAccount = checkingAccountRepository.findById(transferRequest.getDestinationAccount()).get();
        var fromAccountKeycloakId = accountHolderRepository.findById(fromAccount.getPrimaryOwner().getId()).get().getKeycloakId();
        var amount = transferRequest.getAmount();
        validateIsOwner(principal, fromAccountKeycloakId);
        validateEnoughFounds(fromAccount, amount);
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            toAccount.setBalance(toAccount.getBalance().add(amount));
            checkingAccountRepository.save(fromAccount);
            checkingAccountRepository.save(toAccount);
    }


    // VALIDATE SERVICE METHODS
    private void validateEnoughFounds(CheckingAccount fromAccount, BigDecimal amount) {
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds. Please try again.");}
    }

    private void validateIsOwner(Principal principal, String fromAccountKeycloakId) {
        if (!fromAccountKeycloakId.equals(principal.getName())) {
            throw new RuntimeException("You are not the owner of this account. Please try again or create a new account.");}
    }


}
