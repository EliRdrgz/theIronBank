package com.example.theironbank2.service;


import com.example.theironbank2.dto.CheckingAccountDTO;
import com.example.theironbank2.dto.SavingsAccountDTO;

import java.security.Principal;
import java.util.List;

public interface SavingsAccountService {

    List<SavingsAccountDTO> findAllByPrimaryOwner(Principal principal);

}
