package com.example.theironbank2.service;

import com.example.theironbank2.dto.SavingsAccountDTO;
import com.example.theironbank2.model.SavingsAccount;
import com.example.theironbank2.repository.AccountHolderRepository;
import com.example.theironbank2.repository.SavingsAccountRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

    private final AccountHolderRepository accountHolderRepository;
    private final SavingsAccountRepository savingsAccountRepository;

    public SavingsAccountServiceImpl(AccountHolderRepository accountHolderRepository, SavingsAccountRepository savingsAccountRepository) {
        this.accountHolderRepository = accountHolderRepository;
        this.savingsAccountRepository = savingsAccountRepository;
    }


    @Override
    public List<SavingsAccountDTO> findAllByPrimaryOwner(Principal principal) {
        var allMyAccounts = savingsAccountRepository.findByPrimaryOwner_KeycloakId(principal.getName());
        List<SavingsAccountDTO> allMyAccountsDTO = new ArrayList<>();
        for (SavingsAccount account : allMyAccounts) {
            var accountDTO = new SavingsAccountDTO();
            accountDTO.setBalance(account.getBalance());
            accountDTO.setPrimaryOwnerId(account.getPrimaryOwner().getId());
            accountDTO.setId(account.getId());
            accountDTO.setStatus(account.getStatus().toString());
            accountDTO.setCreationDate(account.getCreationDate());
            allMyAccountsDTO.add(accountDTO);
        }
        return allMyAccountsDTO;
    }

}
