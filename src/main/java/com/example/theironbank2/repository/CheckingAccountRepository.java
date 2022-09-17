package com.example.theironbank2.repository;

import com.example.theironbank2.dto.CheckingAccountDTO;
import com.example.theironbank2.model.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {

//    Optional<CheckingAccount> findById(Long id);


//    CheckingAccount create(CheckingAccount checkingAccount);


}
