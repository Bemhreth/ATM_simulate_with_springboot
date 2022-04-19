package com.service.atm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.service.atm.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(path = "api/v1/transactions")
public class operationController {

    @Autowired
    private OperationService operationService;

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(double amount){
        return this.operationService.withdraw(amount);
    }
    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(double amount){
        return this.operationService.deposit(amount);
    }
    @PostMapping("/balance")
    public ResponseEntity<String> balance(){
        return this.operationService.checkBalance();
    }
    @PostMapping("/statement")
    public ResponseEntity<String> printStatement(){
        return this.operationService.printStatement();
    }
    @GetMapping("/logout")
    public void logout(){
        this.operationService.logout();
    }
}
