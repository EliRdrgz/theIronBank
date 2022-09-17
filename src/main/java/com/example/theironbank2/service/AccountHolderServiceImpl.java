package com.example.theironbank2.service;

import com.example.theironbank2.dto.AccountHolderDTO;
import com.example.theironbank2.model.AccountHolder;
import com.example.theironbank2.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

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


}
