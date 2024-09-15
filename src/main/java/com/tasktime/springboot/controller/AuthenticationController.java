package com.tasktime.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasktime.springboot.dto.AuthenticationRequestDto;
import com.tasktime.springboot.security.AuthenticationService;

@RestController
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("/authenticate")
  public String authenticate(@RequestBody AuthenticationRequestDto authRequest) {

    System.out.println("BATATATATA¨SAASTAYSA " + authRequest.toString());

    Authentication authentication = authenticationService.authenticate(authRequest.getUsername(), authRequest.getPassword());
    return authenticationService.generateToken(authentication);
  }
}
