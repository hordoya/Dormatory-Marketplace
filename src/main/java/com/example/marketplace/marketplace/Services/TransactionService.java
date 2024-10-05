package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.Transaction;

public interface TransactionService {
    void confirmBySeller(Long transactionId);

    void confirmByBuyer(Long transactionId);

    Transaction getTransactionById(Long transactionId);

}
