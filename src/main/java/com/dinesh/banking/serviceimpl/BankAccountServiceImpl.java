package com.dinesh.banking.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.dinesh.banking.exception.BankAccountException;
import com.dinesh.banking.model.BankAccount;
import com.dinesh.banking.repository.BankAccountRepository;
import com.dinesh.banking.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	private final BankAccountRepository repository;

	public BankAccountServiceImpl(BankAccountRepository repository) {
	    this.repository = repository;
	}

	
	@Override
    public BankAccount createNewAccountDetails(BankAccount bankAccount) {
        return repository.save(bankAccount);
    }

    @Override
    public BankAccount getByAccountHolderNameAndAccountNumber(
            String accountHolderName,
            String accountNumber) {

        return repository
                .findByAccountHolderNameAndAccountNumber(accountHolderName, accountNumber)
                .orElseThrow(() ->
                        new BankAccountException("Account not found"));
    }

    @Override
    public List<BankAccount> getAllDetails() {
        return repository.findAll();
    }

    @Override
    public BankAccount depositAmount(String accountNumber, Double amount) {
    	
    	if (amount <= 0) {
    	    throw new BankAccountException("Deposit amount must be greater than zero");
    	}
    	
        BankAccount account = repository.findByAccountNumber(accountNumber)
                .orElseGet(() -> {
                    BankAccountException.accountNotFound(accountNumber);
                    return null;
                });

        account.setBalance(account.getBalance() + amount);
        return repository.save(account);
    }

    @Override
    public BankAccount withdrawAmount(String accountNumber, Double amount) {
    	
    	if (amount <= 0) {
    	    throw new BankAccountException("Withdrawal amount must be greater than zero");
    	}
    	
        BankAccount account = repository.findByAccountNumber(accountNumber)
                .orElseGet(() -> {
                    BankAccountException.accountNotFound(accountNumber);
                    return null;
                });

        if (account.getBalance() < amount) {
            BankAccountException.insufficientBalance();
        }

        account.setBalance(account.getBalance() - amount);
        return repository.save(account);
    }

    @Override
    public void deleteByAccountNumber(String accountNumber) {

        if (!repository.existsByAccountNumber(accountNumber)) {
            BankAccountException.accountNotFound(accountNumber);
        }

        repository.deleteByAccountNumber(accountNumber);
    }   
}

