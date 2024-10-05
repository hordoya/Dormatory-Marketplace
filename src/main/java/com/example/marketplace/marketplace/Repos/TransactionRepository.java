package com.example.marketplace.marketplace.Repos;

import com.example.marketplace.marketplace.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
