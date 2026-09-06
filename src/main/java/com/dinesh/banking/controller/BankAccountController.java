package com.dinesh.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dinesh.banking.model.BankAccount;
import com.dinesh.banking.service.BankAccountService;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {

	
	@Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/create")
    public BankAccount createAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.createNewAccountDetails(bankAccount);
    }

    @GetMapping("/all")
    public List<BankAccount> getAllAccounts() {
        return bankAccountService.getAllDetails();
    }

    @GetMapping("/get")
    public BankAccount getByNameAndNumber(
            @RequestParam String accountHolderName,
            @RequestParam String accountNumber) {

        return bankAccountService
                .getByAccountHolderNameAndAccountNumber(accountHolderName, accountNumber);
    }

    @PutMapping("/deposit")
    public BankAccount deposit(
            @RequestParam String accountNumber,
            @RequestParam Double amount) {

        return bankAccountService.depositAmount(accountNumber, amount);
    }

    @PutMapping("/withdraw")
    public BankAccount withdraw(
            @RequestParam String accountNumber,
            @RequestParam Double amount) {

        return bankAccountService.withdrawAmount(accountNumber, amount);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam String accountNumber) {
        bankAccountService.deleteByAccountNumber(accountNumber);
        return "Account deleted successfully";
    }
    
}


