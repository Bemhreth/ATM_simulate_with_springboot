package com.bemhreth.bankservice.repository;


import com.bemhreth.bankservice.models.Account;
import com.bemhreth.bankservice.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM TRANSACTION WHERE account = ?1", nativeQuery = true)
    Account findTransactionByAccount(Account account);
}
