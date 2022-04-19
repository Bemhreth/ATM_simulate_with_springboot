package com.bemhreth.bankservice.repository;


import com.bemhreth.bankservice.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM ACCOUNTS WHERE card_number = ?1", nativeQuery = true)
     Account findAccountByCardNumber(String cardNumber);
}