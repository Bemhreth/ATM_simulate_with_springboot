package com.bemhreth.bankservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private String cardNumber;
    private String transactionStatus;
    private double transactionAmount;
    private String transactionType;
    private LocalDate timestamp;

}
