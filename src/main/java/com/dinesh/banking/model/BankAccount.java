package com.dinesh.banking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BANK_ACCOUNTS")
@Schema(description = "Bank account details")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Unique identifier of the bank account",
            example = "1"
    )
    private Long accountId;

    @NotBlank(message = "Account Holder Name is required")
    @Size(
            min = 3,
            max = 50,
            message = "Name must be between 3 and 50 characters"
    )
    @Pattern(
            regexp = "^[A-Za-z]+(\\s[A-Za-z]+)*$",
            message = "Name can contain only letters and single spaces between words"
    )
    @Column(nullable = false)
    @Schema(
            description = "Name of the account holder",
            example = "Dinesh Karthik"
    )
    private String accountHolderName;

    @NotBlank(message = "Account number is required")
    @Pattern(
            regexp = "^[0-9]{10,18}$",
            message = "Account number must contain 10 to 18 digits only"
    )
    @Column(nullable = false, unique = true, updatable = false)
    @Schema(
            description = "Unique bank account number",
            example = "123456789012"
    )
    private String accountNumber;

    @NotNull(message = "Balance is required")
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "Balance cannot be negative"
    )
    @Column(nullable = false)
    @Schema(
            description = "Current account balance",
            example = "25000.00",
            minimum = "0"
    )
    private Double balance;

    @NotBlank(message = "Branch name is required")
    @Size(
            min = 3,
            max = 50,
            message = "Branch name must be between 3 and 50 characters"
    )
    @Pattern(
            regexp = "^[A-Za-z]+(\\s[A-Za-z]+)*$",
            message = "Branch name can contain only letters and spaces"
    )
    @Column(nullable = false)
    @Schema(
            description = "Bank branch associated with the account",
            example = "Chennai Main Branch"
    )
    private String branchName;
    
}