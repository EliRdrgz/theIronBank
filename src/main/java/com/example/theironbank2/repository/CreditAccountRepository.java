package com.example.theironbank2.repository;

import com.example.theironbank2.model.CreditAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditAccountRepository extends JpaRepository<CreditAccount, Long> {

    List<CreditAccount> findByPrimaryOwner_KeycloakId(String holderId);

}

