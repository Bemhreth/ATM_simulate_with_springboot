package com.service.atm.dto;


import lombok.Getter;
import lombok.Setter;
import com.atm.emulator.model.Transaction;


import java.util.Collection;

@Getter
@Setter
public class Account {


    private int bankId;
    private int customerId;
    private String cardNumber;
    private String cardType;
    private double cardBalance;
    private String expireDate;
    private String issueDate;
    private Collection<Transaction> transactions;

    public Account() {

    }
    public Account(int bankId, int customerId, String cardNumber, String cardType, double cardBalance, String expireDate, String issueDate) {
        super();
        this.bankId = bankId;
        this.customerId = customerId;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.cardBalance = cardBalance;
        this.expireDate = expireDate;
        this.issueDate = issueDate;


    }
}
