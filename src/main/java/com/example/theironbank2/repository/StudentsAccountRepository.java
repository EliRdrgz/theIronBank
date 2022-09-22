package com.example.theironbank2.repository;

import com.example.theironbank2.model.CreditAccount;
import com.example.theironbank2.model.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentsAccountRepository extends JpaRepository<StudentAccount, Long> {


    Optional<StudentAccount> findById(Optional<CreditAccount> fromAccountId);

    List<StudentAccount> findByPrimaryOwner_KeycloakId(String holderId);

}

