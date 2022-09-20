package com.example.theironbank2.dto;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferDTO {

    private String status;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private String description;


}
