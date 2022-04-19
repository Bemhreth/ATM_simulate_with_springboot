package com.bemhreth.bankservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String address;
    private String code;
    @OneToMany(mappedBy="bank")
    private Set<Account> accounts;

    @OneToMany(mappedBy="bank")
    private Set<ATM> atms;


}
