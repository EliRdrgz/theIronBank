package com.example.theironbank2.service;

import com.example.theironbank2.dto.CheckingAccountDTO;
import com.example.theironbank2.model.CheckingAccount;
import com.example.theironbank2.repository.AccountHolderRepository;
import com.example.theironbank2.repository.CheckingAccountRepository;
import com.example.theironbank2.security.requests.CreateAccountRequest;

import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckingAccountServiceImpl implements CheckingAccountService {


    private final AccountHolderRepository accountHolderRepository;


    private final CheckingAccountRepository checkingAccountRepository;

    public CheckingAccountServiceImpl(AccountHolderRepository accountHolderRepository, CheckingAccountRepository checkingAccountRepository) {
        this.accountHolderRepository = accountHolderRepository;
        this.checkingAccountRepository = checkingAccountRepository;
    }


    //    I want to create a method to find all accounts by primary owner that is the user logged in.
    @Override
    public List<CheckingAccountDTO> findAllByPrimaryOwner(Principal principal) {
        var allMyAccounts = checkingAccountRepository.findByPrimaryOwner_KeycloakId(principal.getName());
        List<CheckingAccountDTO> allMyAccountsDTO = new ArrayList<>();
        for (CheckingAccount account : allMyAccounts) {
            var accountDTO = new CheckingAccountDTO();
            accountDTO.setBalance(account.getBalance());
            accountDTO.setPrimaryOwnerId(account.getPrimaryOwner().getId());
            accountDTO.setId(account.getId());
            accountDTO.setStatus(account.getStatus().toString());
            allMyAccountsDTO.add(accountDTO);
        }
        return allMyAccountsDTO;
    }

    @Override
    public CheckingAccountDTO create(CheckingAccountDTO checkingAccountDTO) {
        return null;
    }

    @Override
    public CheckingAccountDTO createAccount(CreateAccountRequest createAccountRequest) {
        return null;
    }


}
