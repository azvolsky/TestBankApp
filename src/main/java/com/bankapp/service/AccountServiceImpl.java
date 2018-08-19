package com.bankapp.service;

import com.bankapp.dao.AccountDAO;
import com.bankapp.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO dao;


    @Override
    public Optional<Account> retrieveAccountById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<Account> retrieveAllAccounts() {
        return dao.findAll();
    }

    @Override
    public List<Account> retrieveAccountsByClientId(Integer id) {
        return dao.findByClientId(id);
    }

    @Override
    public Account updateAccount(Account account) {
        return dao.save(account);
    }

    @Override
    public Boolean deleteAccountById(Integer id) {
        dao.deleteById(id);
        return !dao.existsById(id);
    }

    @Override
    public List<Account> findByClientId(Integer id) {
        return dao.findByClientId(id);
    }
}
