package com.bemhreth.bankservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    public class Transaction {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        @ManyToOne
        @JoinColumn(name="account_id", nullable=false)
        private Account account;
        private double transactionAmount;
        private String transactionStatus;
        private String transactionType;
        private LocalDate timestamp;

        @Override
        public String toString() {
            return "Transaction [id=" + id + ", Transaction Amount = " + transactionAmount + ", Transaction Type = " + transactionType + ", Transaction Status = " + transactionStatus
                    + "]";
        }
}
