package com.example.theironbank2.security.requests;

import com.example.theironbank2.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {

    private Long originAccount;
    private AccountType originAccountType;
    private Long destinationAccount;
    private AccountType destinationAccountType;
    private BigDecimal amount;
    private String description;

}
