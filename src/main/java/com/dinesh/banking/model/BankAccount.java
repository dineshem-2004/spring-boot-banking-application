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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "BANK_ACCOUNTS")
public class BankAccount {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long accountId;
	
	@NotBlank(message = "Account Holder Name is required")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	@Pattern(regexp = "^[A-Za-z]+(\\s[A-Za-z]+)*$", 
				message = "Name can contains only letters and single spaces bettween words")
	@Column(nullable = false)
	private String accountHolderName;
	
	@NotBlank(message = "Account number is required")
	@Pattern(regexp = "^[0-9]{10,18}$",
				message = "Account number must contains 10 to 18 digits only")
	@Column (nullable = false, unique = true, updatable = false)
	private String accountNumber;
	
	@NotNull(message = "Balance is required")
	@DecimalMin(value = "0.0", inclusive = true, message = "Balance cannot be negative")
	@Column(nullable = false)
	private Double balance;
	
	@NotBlank(message = "Branch name is required")
	@Size(min = 3, max = 50,
				message = "Branch name must be between 3 and 50 characters")
	@Pattern(regexp = "^[A-Za-z]+(\\s[A-Za-z]+)*$",
				message = "Branch name can contains only letters and spaces")
	@Column(nullable = false)
	private String branchName;
	
}

