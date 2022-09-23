package com.example.theironbank2.dto;

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
public class SavingsAccountDTO {

    private Long id;
    private BigDecimal balance;
    private Instant creationDate;
    private String status;
    private Long primaryOwnerId;

}
