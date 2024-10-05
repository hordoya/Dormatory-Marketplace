package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.Transaction;
import com.example.marketplace.marketplace.Repos.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void confirmBySeller(Long transactionId) {
        // Fetch the Transaction object instead of Product

        Transaction transaction = this.transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setSellerConfirmed(true);


        // If both seller and buyer have confirmed, mark as complete
        if (transaction.isTransactionComplete()) {
            // Mark transaction as complete (or update status if needed)
        }

        this.transactionRepository.save(transaction);
    }

    @Override
    public void confirmByBuyer(Long transactionId) {
        // Fetch the Transaction object instead of Product
        Transaction transaction = this.transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setBuyerConfirmed(true);

        // If both seller and buyer have confirmed, mark as complete
        if (transaction.isTransactionComplete()) {
            // Mark transaction as complete (or update status if needed)
        }

        this.transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        return this.transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }
}
