package com.example.theironbank2.service;

import com.example.theironbank2.dto.CheckingAccountDTO;
import com.example.theironbank2.enums.AccountType;
import com.example.theironbank2.model.AccountHolder;
import com.example.theironbank2.model.CheckingAccount;
import com.example.theironbank2.repository.AccountHolderRepository;
import com.example.theironbank2.repository.CheckingAccountRepository;
import com.example.theironbank2.security.requests.CreateAccountRequest;

import com.example.theironbank2.security.requests.ReadBalanceRequest;
import com.example.theironbank2.security.requests.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    CheckingAccountRepository checkingAccountRepository;


    @Override
    public CheckingAccountDTO createAccount(CreateAccountRequest createAccountRequest) {
        var ownerId = createAccountRequest.getAccountHolderId();
        var type = createAccountRequest.getAccountType();
        Optional<AccountHolder> owner = accountHolderRepository.findById(ownerId);
        if (owner.isEmpty()) {
           throw new RuntimeException("Owner not found. Please try again or create a new owner.");
        } else {
            if(type == AccountType.CHECKING) {
                var entity = new CheckingAccount();
                entity.setBalance(createAccountRequest.getBalance());
                entity.setPrimaryOwner(owner.get());
                entity.setStatus(createAccountRequest.getStatus());
                var storedCheckingAccount = checkingAccountRepository.save(entity);
                var checkingAccountDTO = new CheckingAccountDTO();
                checkingAccountDTO.setId(storedCheckingAccount.getId());
                checkingAccountDTO.setCreationDate(storedCheckingAccount.getCreationDate());
                checkingAccountDTO.setPenaltyFee(storedCheckingAccount.getPenaltyFee());
                checkingAccountDTO.setBalance(storedCheckingAccount.getBalance());
                checkingAccountDTO.setPrimaryOwner(storedCheckingAccount.getPrimaryOwner());
                checkingAccountDTO.setStatus(String.valueOf(storedCheckingAccount.getStatus()));
                return checkingAccountDTO;

            } else{
                throw new RuntimeException("Account type not found. Please try again or create a new account type.");
            }
       }

    }
    //TODO PRINCIPAL
    @Override
    public Optional<CheckingAccount> checkBalance(ReadBalanceRequest readBalanceRequest) {
        var accountId = readBalanceRequest.getAccountId();
        var holderId = readBalanceRequest.getAccountHolderId();
        Optional<AccountHolder> owner = accountHolderRepository.findById(holderId);
        Optional<CheckingAccount> account = checkingAccountRepository.findById(accountId);
        if (owner.isEmpty()) {
            throw new RuntimeException("Owner not found. Please try again or create a new owner.");
        } else if (account.isEmpty()) {
            throw new RuntimeException("Account not found. Please try again or create a new account.");
        } else if (account.get().getPrimaryOwner().getId() != holderId) {
            throw new RuntimeException("You are not the owner of this account. Please try again or create a new account.");
        } else {
            var accountToShow = checkingAccountRepository.findById(accountId);
            return accountToShow;


        }
    }
//I want to add a method to transfer money from one account to another.
    @Override
    public void makeTransfer(TransferRequest transferRequest) {
        var fromAccount = checkingAccountRepository.findById(transferRequest.getFromAccount());
        var toAccount = checkingAccountRepository.findById(transferRequest.getToAccount());
        if (fromAccount.isEmpty()) {
            throw new RuntimeException("From account not found. Please try again or create a new account.");
        } else if (toAccount.isEmpty()) {
            throw new RuntimeException("To account not found. Please try again or create a new account.");
        } else if (fromAccount.get().getBalance().compareTo(transferRequest.getAmount()) > 1) {
            throw new RuntimeException("Insufficient funds. Please try again or create a new account.");
        } else {
            var fromAccountBalance = fromAccount.get().getBalance();
            var toAccountBalance = toAccount.get().getBalance();
            fromAccount.get().setBalance(fromAccountBalance.subtract(transferRequest.getAmount()));
            toAccount.get().setBalance(toAccountBalance.add(transferRequest.getAmount()));
            System.out.println("Transfer successful");
            System.out.println("From account balance: " + fromAccount.get().getBalance());
            System.out.println("To account balance: " + toAccount.get().getBalance());
            checkingAccountRepository.save(fromAccount.get());
            checkingAccountRepository.save(toAccount.get());

        }
    }

//    @Override
//    public CheckingAccountDTO makeTransfer(ReadBalanceRequest readBalanceRequest, Principal principal) {
//        return null;
//    }

//    I want to create a method to make a transfer between two accounts from different holders.




//    @Override
//    public CheckingAccountDTO modifyBalance(CreateUserRequest.ModifyBalanceRequest modifyBalanceRequest) {
//        var ownerId = modifyBalanceRequest.getAccountId();
//
//
//
//        return null;
//    }
}
