package com.example.theironbank2.service;

import com.example.theironbank2.dto.CheckingAccountDTO;
import com.example.theironbank2.security.requests.CreateAccountRequest;


import java.math.BigDecimal;
import java.util.List;

public interface CheckingAccountService {

    CheckingAccountDTO create(CheckingAccountDTO checkingAccountDTO);
    List<CheckingAccountDTO>findAll();

    List<CheckingAccountDTO>findByStatus(String status);

    List<CheckingAccountDTO>findByBalanceGreaterThanEqual(BigDecimal balance);

    List<CheckingAccountDTO>findByBalanceLessThanEqual(BigDecimal balance);

    List<CheckingAccountDTO> findByPrimaryOwner(Long id);

    List<CheckingAccountDTO> findBySecondaryOwner(Long id);


    CheckingAccountDTO createAccount(CreateAccountRequest createAccountRequest);
}
