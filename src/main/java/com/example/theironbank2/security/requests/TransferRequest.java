package com.example.theironbank2.security.requests;

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

    private Long fromAccount;
    private Long toAccount;
    private BigDecimal amount;



}
