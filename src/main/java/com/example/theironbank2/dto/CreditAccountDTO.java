package com.example.theironbank2.dto;

import com.example.theironbank2.model.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreditAccountDTO {

    private Long id;
    private BigDecimal balance;
    private String creationDate;
    private String status;
    private AccountHolder primaryOwner;
    private Long primaryOwnerId;
    private BigDecimal interestRate;

}
