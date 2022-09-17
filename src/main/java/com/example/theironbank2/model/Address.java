package com.example.theironbank2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String road;
    private Integer number;
    private Long postalCode;
    private String country;

}
