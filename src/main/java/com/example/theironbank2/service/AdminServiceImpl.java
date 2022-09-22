package com.example.theironbank2.service;

import com.example.theironbank2.dto.CheckingAccountDTO;
import com.example.theironbank2.dto.SavingsAccountDTO;
import com.example.theironbank2.dto.TransferDTO;
import com.example.theironbank2.enums.AccountType;
import com.example.theironbank2.model.AccountHolder;
import com.example.theironbank2.model.CheckingAccount;
import com.example.theironbank2.model.SavingsAccount;
import com.example.theironbank2.repository.AccountHolderRepository;
import com.example.theironbank2.repository.CheckingAccountRepository;
import com.example.theironbank2.repository.SavingsAccountRepository;
import com.example.theironbank2.security.requests.CreateAccountRequest;

import com.example.theironbank2.security.requests.ReadBalanceRequest;
import com.example.theironbank2.security.requests.TransferRequest;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Log
public class AdminServiceImpl implements AdminService {


    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    SavingsAccountRepository savingsAccountRepository;


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

    @Override
    public SavingsAccountDTO createSavingsAccount(CreateAccountRequest createAccountRequest) {
        var ownerId = createAccountRequest.getAccountHolderId();
        var type = createAccountRequest.getAccountType();
        Optional<AccountHolder> owner = accountHolderRepository.findById(ownerId);
        if (owner.isEmpty()) {
            throw new RuntimeException("Owner not found. Please try again or create a new owner.");
        } else {
            if(type == AccountType.SAVINGS) {
                var entity = new SavingsAccount();
                entity.setBalance(createAccountRequest.getBalance());
                entity.setPrimaryOwner(owner.get());
                entity.setStatus(createAccountRequest.getStatus());
                var storedCheckingAccount = savingsAccountRepository.save(entity);
                var savingsAccountDTO = new SavingsAccountDTO();
                savingsAccountDTO.setId(storedCheckingAccount.getId());
                savingsAccountDTO.setCreationDate(storedCheckingAccount.getCreationDate());
                savingsAccountDTO.setBalance(storedCheckingAccount.getBalance());
                savingsAccountDTO.setPrimaryOwner(storedCheckingAccount.getPrimaryOwner());
                savingsAccountDTO.setStatus(String.valueOf(storedCheckingAccount.getStatus()));
                return savingsAccountDTO;

            } else{
                throw new RuntimeException("Account type not found. Please try again or create a new account type.");
            }
        }

    }

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
            return checkingAccountRepository.findById(accountId);


        }
    }

    @Override
    public TransferDTO makeTransfer(TransferRequest transferRequest) {
        var fromAccount = checkingAccountRepository.findById(transferRequest.getOriginAccount());
        var toAccount = checkingAccountRepository.findById(transferRequest.getDestinationAccount());
        if (fromAccount.isEmpty()) {
            throw new RuntimeException("Origin account not found. Please try again or create a new account.");
        } else if (toAccount.isEmpty()) {
            throw new RuntimeException("Destination account not found. Please try again or create a new account.");
        } else if (fromAccount.get().getBalance().compareTo(transferRequest.getAmount()) < 1) {
            throw new RuntimeException("Insufficient funds. Please try again or create a new account.");
        } else {
            var fromAccountBalance = fromAccount.get().getBalance();
            var toAccountBalance = toAccount.get().getBalance();
            fromAccount.get().setBalance(fromAccountBalance.subtract(transferRequest.getAmount()));
            toAccount.get().setBalance(toAccountBalance.add(transferRequest.getAmount()));
            checkingAccountRepository.save(fromAccount.get());
            checkingAccountRepository.save(toAccount.get());
            var transferDTO = new TransferDTO();
            transferDTO.setStatus("Transfer successful");
            transferDTO.setFromAccount(String.valueOf(fromAccount.get().getId()));
            transferDTO.setToAccount(String.valueOf(toAccount.get().getId()));
            transferDTO.setAmount(transferRequest.getAmount());
            transferDTO.setDescription(transferRequest.getDescription());
            return transferDTO;

        }
    }

    @Override
    public TransferDTO saveMoney(TransferRequest transferRequest) {
        var fromAccount = checkingAccountRepository.findById(transferRequest.getOriginAccount());
        var toAccount = savingsAccountRepository.findById(transferRequest.getDestinationAccount());
        var fromAccountType = transferRequest.getOriginAccountType();
        var toAccountType = transferRequest.getDestinationAccountType();
        Optional<AccountHolder> owner = accountHolderRepository.findById(fromAccount.get().getPrimaryOwner().getId());
        Optional<AccountHolder> owner2 = accountHolderRepository.findById(toAccount.get().getPrimaryOwner().getId());
        if (fromAccount.isEmpty()) {
            throw new RuntimeException("Origin account not found. Please try again or create a new account.");
        } else if (toAccount.isEmpty()) {
            throw new RuntimeException("Destination savings account not found. Please try again or create a new savings account.");
        } else if (fromAccount.get().getBalance().compareTo(transferRequest.getAmount()) < 1) {
            throw new RuntimeException("Insufficient funds. Please try again or create a new account.");
        } else {
            if(owner.equals(owner2)){
            var fromAccountBalance = fromAccount.get().getBalance();
            var toAccountBalance = toAccount.get().getBalance();
            fromAccount.get().setBalance(fromAccountBalance.subtract(transferRequest.getAmount()));
            toAccount.get().setBalance(toAccountBalance.add(transferRequest.getAmount()));
            checkingAccountRepository.save(fromAccount.get());
            savingsAccountRepository.save(toAccount.get());
            var transferDTO = new TransferDTO();
            transferDTO.setStatus("Congratulations, you have just saved money!");
            transferDTO.setFromAccount(String.valueOf(fromAccount.get().getId()));
            transferDTO.setFromAccountType(fromAccountType);
            transferDTO.setToAccount(String.valueOf(toAccount.get().getId()));
            transferDTO.setToAccountType(toAccountType);
            transferDTO.setAmount(transferRequest.getAmount());
            transferDTO.setDescription(transferRequest.getDescription());
            log.info("Account {} has just saved money to account {}"+ fromAccount.get().getId().toString() + toAccount.get().getId().toString());
            return transferDTO;}
            else{
                throw new RuntimeException("You are not the owner of this account. Please try again or create a new account.");
            }

        }
    }
}
