package com.example.theironbank2.dto;

import com.example.theironbank2.model.AccountHolder;
import com.example.theironbank2.model.CheckingAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CheckingAccountDTO {

    private Long id;
    private BigDecimal balance;
    private Instant creationDate;
    private String status;
    private Long primaryOwnerId;

}
