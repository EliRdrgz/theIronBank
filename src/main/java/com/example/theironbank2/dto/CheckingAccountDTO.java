package com.example.theironbank2.dto;

import com.example.theironbank2.model.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CheckingAccountDTO {

    private Long id;
    private BigDecimal balance;
    private int penaltyFee;
    private Instant creationDate;
    private String status;
    private AccountHolder primaryOwner;




}
