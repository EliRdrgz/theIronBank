package com.example.theironbank2.controller;

import com.example.theironbank2.security.config.KeycloakProvider;
import com.example.theironbank2.security.requests.LoginRequest;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;

@RestController
@RequestMapping("/members")
public class UserController {



    private final KeycloakProvider kcProvider;

    public UserController(KeycloakProvider kcProvider) {
        this.kcProvider = kcProvider;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("/get-token")
    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody LoginRequest loginRequest) {
        Keycloak keycloak = kcProvider.newKeycloakBuilderWithPasswordCredentials(loginRequest.getUsername(), loginRequest.getPassword()).build();

        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }

    }

}
