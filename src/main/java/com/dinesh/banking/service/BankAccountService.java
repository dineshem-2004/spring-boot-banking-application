package com.dinesh.banking.service;

import java.util.List;

import com.dinesh.banking.model.BankAccount;

public interface BankAccountService {

    BankAccount createNewAccountDetails(BankAccount bankAccount);

    List<BankAccount> getAllDetails();

    BankAccount getByAccountHolderNameAndAccountNumber(
            String accountHolderName,
            String accountNumber
    );

    BankAccount depositAmount(String accountNumber, Double amount);

    BankAccount withdrawAmount(String accountNumber, Double amount);

    void deleteByAccountNumber(String accountNumber);
    
}