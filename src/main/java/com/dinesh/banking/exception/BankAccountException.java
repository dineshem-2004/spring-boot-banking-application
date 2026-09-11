package com.dinesh.banking.exception;

public class BankAccountException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BankAccountException(String message) {
        super(message);
    }

    public static void accountNotFound(String accountNumber) {
        throw new BankAccountException(
                "Bank account not found with account number: " + accountNumber
        );
    }

    public static void insufficientBalance() {
        throw new BankAccountException(
                "Insufficient balance to withdraw the requested amount"
        );
    }
    
}