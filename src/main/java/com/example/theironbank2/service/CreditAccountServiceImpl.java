package com.example.theironbank2.service;

import com.example.theironbank2.dto.CreditAccountDTO;
import com.example.theironbank2.dto.SavingsAccountDTO;
import com.example.theironbank2.model.CreditAccount;
import com.example.theironbank2.model.SavingsAccount;
import com.example.theironbank2.repository.AccountHolderRepository;
import com.example.theironbank2.repository.CreditAccountRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreditAccountServiceImpl implements CreditAccountService {

    private final AccountHolderRepository accountHolderRepository;
    private final CreditAccountRepository creditAccountRepository;

    public CreditAccountServiceImpl(AccountHolderRepository accountHolderRepository, CreditAccountRepository creditAccountRepository) {
        this.accountHolderRepository = accountHolderRepository;
        this.creditAccountRepository = creditAccountRepository;
    }


    @Override
    public List<CreditAccountDTO> findAllByPrimaryOwner(Principal principal) {
        var allMyAccounts = creditAccountRepository.findByPrimaryOwner_KeycloakId(principal.getName());
        List<CreditAccountDTO> allMyAccountsDTO = new ArrayList<>();
        for (CreditAccount account : allMyAccounts) {
            var accountDTO = new CreditAccountDTO();
            accountDTO.setBalance(account.getBalance());
            accountDTO.setCreationDate(account.getCreationDate().toString());
            accountDTO.setPrimaryOwnerId(account.getPrimaryOwner().getId());
            accountDTO.setPrimaryOwner(account.getPrimaryOwner());
            accountDTO.setId(account.getId());
            accountDTO.setStatus(account.getStatus().toString());
            accountDTO.setInterestRate(account.getInterestRate());
            allMyAccountsDTO.add(accountDTO);
        }
        return allMyAccountsDTO;
    }

}
