package com.atm.emulator.model;


import com.service.atm.dto.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {


     private Account account;
    private String transactionAmount;
    private String transactionType;


    public Transaction() {
    }

    public Transaction(String transactionAmount, String transactionType) {
        super();
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
    }


    @Override
    public String toString() {
        return "Transaction [ Account" + account + " Transaction Amount = " + transactionAmount + ", Transaction Type = " + transactionType
                + "]";
    }
}
