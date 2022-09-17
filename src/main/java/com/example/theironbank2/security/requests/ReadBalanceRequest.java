package com.example.theironbank2.security.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadBalanceRequest {

    private Long accountId;
    private Long accountHolderId;


}
