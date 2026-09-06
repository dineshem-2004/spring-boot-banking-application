package com.dinesh.banking.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dinesh.banking.model.BankAccount;



@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
	
	Optional<BankAccount> findByAccountHolderNameAndAccountNumber(String accountHolderName, String accountNumber);
	
	Optional<BankAccount> findByAccountNumber(String accountNumber);
	
	boolean existsByAccountNumber(String accountNumber);
	
	@Transactional
	@Modifying
	void deleteByAccountNumber(String accountNumber);
	
}

