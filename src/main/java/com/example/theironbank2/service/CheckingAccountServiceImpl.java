package com.example.theironbank2.service;

import com.example.theironbank2.dto.CheckingAccountDTO;
import com.example.theironbank2.security.requests.CreateAccountRequest;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CheckingAccountServiceImpl implements CheckingAccountService {

    @Override
    public CheckingAccountDTO create(CheckingAccountDTO checkingAccountDTO) {
        return null;
    }

    @Override
    public List<CheckingAccountDTO> findAll() {
        return null;
    }

    @Override
    public List<CheckingAccountDTO> findByStatus(String status) {
        return null;
    }

    @Override
    public List<CheckingAccountDTO> findByBalanceGreaterThanEqual(BigDecimal balance) {
        return null;
    }

    @Override
    public List<CheckingAccountDTO> findByBalanceLessThanEqual(BigDecimal balance) {
        return null;
    }

    @Override
    public List<CheckingAccountDTO> findByPrimaryOwner(Long id) {
        return null;
    }

    @Override
    public List<CheckingAccountDTO> findBySecondaryOwner(Long id) {
        return null;
    }


    @Override
    public CheckingAccountDTO createAccount(CreateAccountRequest createAccountRequest) {
        return null;
    }
}
