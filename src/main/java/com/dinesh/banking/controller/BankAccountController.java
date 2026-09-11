package com.dinesh.banking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.dinesh.banking.model.BankAccount;
import com.dinesh.banking.service.BankAccountService;

@RestController
@RequestMapping("/bankAccount")
@Tag(
        name = "Bank Account",
        description = "APIs for managing bank accounts and account transactions"
)
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @Operation(
            summary = "Create a new bank account",
            description = "Creates and saves a new bank account with the provided account details."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Bank account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid account details")
    })
    @PostMapping("/create")
    public ResponseEntity<BankAccount> createAccount(
            @RequestBody BankAccount bankAccount) {

        BankAccount createdAccount =
                bankAccountService.createNewAccountDetails(bankAccount);

        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all bank accounts",
            description = "Retrieves all bank accounts available in the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bank accounts retrieved successfully")
    })
    @GetMapping("/all")
    public ResponseEntity<List<BankAccount>> getAllAccounts() {

        List<BankAccount> accounts =
                bankAccountService.getAllDetails();

        return ResponseEntity.ok(accounts);
    }

    @Operation(
            summary = "Get a bank account",
            description = "Retrieves a bank account using the account holder name and account number."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bank account retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Account not found")
    })
    @GetMapping("/get")
    public ResponseEntity<BankAccount> getByNameAndNumber(
            @RequestParam String accountHolderName,
            @RequestParam String accountNumber) {

        BankAccount account =
                bankAccountService
                        .getByAccountHolderNameAndAccountNumber(
                                accountHolderName,
                                accountNumber
                        );

        return ResponseEntity.ok(account);
    }

    @Operation(
            summary = "Deposit money",
            description = "Deposits the specified amount into an existing bank account."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Amount deposited successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid amount or account not found")
    })
    @PutMapping("/deposit")
    public ResponseEntity<BankAccount> deposit(
            @RequestParam String accountNumber,
            @RequestParam Double amount) {

        BankAccount account =
                bankAccountService.depositAmount(
                        accountNumber,
                        amount
                );

        return ResponseEntity.ok(account);
    }

    @Operation(
            summary = "Withdraw money",
            description = "Withdraws the specified amount from an existing bank account after validating the available balance."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Amount withdrawn successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid amount, account not found, or insufficient balance")
    })
    @PutMapping("/withdraw")
    public ResponseEntity<BankAccount> withdraw(
            @RequestParam String accountNumber,
            @RequestParam Double amount) {

        BankAccount account =
                bankAccountService.withdrawAmount(
                        accountNumber,
                        amount
                );

        return ResponseEntity.ok(account);
    }

    @Operation(
            summary = "Delete a bank account",
            description = "Deletes an existing bank account using the account number."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Bank account deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Account not found")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(
            @RequestParam String accountNumber) {

        bankAccountService.deleteByAccountNumber(accountNumber);

        return ResponseEntity.noContent().build();
    }
}