package com.example.theironbank2.security.requests;

import com.example.theironbank2.enums.AccountType;
import com.example.theironbank2.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest {

    Long accountHolderId;

    @Enumerated(EnumType.STRING)
    AccountType accountType;

    BigDecimal balance;

    private String dateOfBirth;

//    String secretKey;

    @Enumerated(EnumType.STRING)
    Status status;




}
