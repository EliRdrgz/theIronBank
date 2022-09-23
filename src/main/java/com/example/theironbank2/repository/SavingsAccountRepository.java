package com.example.theironbank2.repository;

import com.example.theironbank2.model.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

    List<SavingsAccount> findByPrimaryOwner_KeycloakId(String holderId);

}

