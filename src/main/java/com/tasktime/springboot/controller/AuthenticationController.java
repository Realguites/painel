package com.tasktime.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasktime.springboot.dto.AuthenticationRequestDto;
import com.tasktime.springboot.model.LoggedUserToken;
import com.tasktime.springboot.security.AuthenticationService;

@RestController
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("/authenticate")
  public ResponseEntity<LoggedUserToken> authenticate(@RequestBody AuthenticationRequestDto authRequest) {
    Authentication authentication = authenticationService.authenticate(authRequest.getUsername(), authRequest.getPassword());
    LoggedUserToken loggedUserToken = new LoggedUserToken(authRequest.getUsername(), authenticationService.generateToken(authentication));
    return ResponseEntity.ok(loggedUserToken);
  }
}
