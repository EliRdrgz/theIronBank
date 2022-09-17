package com.example.theironbank2.service;

import com.example.theironbank2.dto.AccountHolderDTO;
import com.example.theironbank2.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface AccountHolderService {

    AccountHolderDTO create(AccountHolderDTO accountHolderDTO);


}
