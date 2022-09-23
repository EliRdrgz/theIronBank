package com.example.theironbank2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class AccountHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keycloakId;

    private String name;

    private String dateOfBirth;
    

    @Embedded
    private Address primaryAddress;

    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    private List<CheckingAccount> checkingAccounts;


}
