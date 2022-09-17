package com.example.theironbank2.security.requests;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    String username;
    String password;
    String email;
    String firstname;
    String lastname;

}
