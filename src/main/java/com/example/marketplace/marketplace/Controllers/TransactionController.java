package com.example.marketplace.marketplace.Controllers;

import com.example.marketplace.marketplace.Models.Transaction;
import com.example.marketplace.marketplace.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @GetMapping("/{id}")
    public String showTransactionDetails(@PathVariable("id") Long transactionId, Model model) {
        Transaction transaction = this.transactionService.getTransactionById(transactionId);
        model.addAttribute("transaction", transaction);
        return "transaction-details";  // Corresponds to the Thymeleaf template: transaction-details.html
    }

    @PostMapping("/{id}/confirmSeller")
    public String confirmSeller(@PathVariable("id") Long transactionId) {
        this.transactionService.confirmBySeller(transactionId);
        Transaction transaction = new Transaction();
        transaction.setId(transaction.getId());
        return "redirect:/";
    }

    @PostMapping("/{id}/confirmBuyer")
    public String confirmBuyer(@PathVariable("id") Long transactionId) {
        Transaction transaction = new Transaction();
        transaction.setId(transaction.getId());
        this.transactionService.confirmByBuyer(transactionId);

        return "redirect:/";
    }

}
