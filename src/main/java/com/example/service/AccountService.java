package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account checkForDup(Account account){
        if(accountRepository.findAccountByUsername(account.getUsername()) != null){
            return null;
        }
        return account;

    }

    public Account Register(Account account){
        Account ac = accountRepository.findAccountByUsername(account.getUsername());
        if(ac != null || account.getPassword().length() < 4 || account.getUsername() ==null || account.getUsername().isBlank() || account.getPassword() == null){
            return null;
        }
        return accountRepository.save(account);
    } 

    public Account Login(Account account){
        Account acc = accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        return acc;
    }
}
