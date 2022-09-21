package com.example.theironbank2.dto;

import com.example.theironbank2.enums.AccountType;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferDTO {

    private String status;
    private String fromAccount;
    private AccountType fromAccountType;
    private String toAccount;
    private AccountType toAccountType;
    private BigDecimal amount;
    private String description;

}
