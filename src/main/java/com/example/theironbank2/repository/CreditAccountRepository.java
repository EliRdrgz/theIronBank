package com.example.theironbank2.repository;

import com.example.theironbank2.model.CreditAccount;
import com.example.theironbank2.model.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditAccountRepository extends JpaRepository<CreditAccount, Long> {


    Optional<CreditAccount> findById(Optional<CreditAccount> fromAccountId);

    List<CreditAccount> findByPrimaryOwner_KeycloakId(String holderId);

}

