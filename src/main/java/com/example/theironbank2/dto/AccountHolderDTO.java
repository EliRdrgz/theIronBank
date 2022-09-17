package com.example.theironbank2.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountHolderDTO {

    private Long id;
    private String keycloakId;
    private String name;
    private String dateOfBirth;
    //Address
    private String road;
    private Integer number;
    private Long postalCode;
    private String country;

}
