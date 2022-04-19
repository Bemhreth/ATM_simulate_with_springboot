package com.bemhreth.bankservice.controllers;

import egs.henokcodes.bankservice.dto.*;
import egs.henokcodes.bankservice.models.Account;
import egs.henokcodes.bankservice.models.Transaction;
import egs.henokcodes.bankservice.repository.AccountRepository;
import egs.henokcodes.bankservice.services.MyUserDetailsService;

import egs.henokcodes.bankservice.services.OperationService;
import egs.henokcodes.bankservice.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class OperationController {
    Logger logger = LoggerFactory.getLogger(OperationController.class);

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private OperationService operationService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @GetMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }


    @PostMapping(value = "/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(@RequestHeader("Authorization") String token,@RequestBody TransactionRequest transactionRequest) throws Exception {
       return this.operationService.withdraw(token,transactionRequest);
    }
    @PostMapping(value = "/deposit")
    public ResponseEntity<TransactionResponse> deposit(@RequestHeader("Authorization") String token,@RequestBody TransactionRequest transactionRequest) throws Exception {
        return this.operationService.deposit(token,transactionRequest);
    }


        @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getCardNumber(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect credentials", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getCardNumber());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
