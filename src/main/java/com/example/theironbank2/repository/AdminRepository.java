package com.example.theironbank2.repository;


import com.example.theironbank2.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByName(String username);


}
