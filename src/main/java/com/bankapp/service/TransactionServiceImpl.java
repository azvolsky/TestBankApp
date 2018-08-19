package com.bankapp.service;

import com.bankapp.dao.TransactionDAO;
import com.bankapp.entity.Account;
import com.bankapp.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDAO dao;

    @Override
    public Transaction update(Transaction tr) {
        return dao.save(tr);
    }

    @Override
    public Optional<Transaction> retrieveById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<Transaction> retrieveAll() {
        return dao.findAll();
    }

    @Override
    public List<Transaction> retrieveByAccountAll(Account account) {
        List<Transaction> trList = dao.findByAccountDst(account);
        trList.addAll(dao.findByAccountSrc(account));
        return trList;
    }

    @Override
    public List<Transaction> retrieveByAccountSrc(Account account) {
        return dao.findByAccountSrc(account);
    }

    @Override
    public List<Transaction> retrieveByAccountDst(Account account) {
        return dao.findByAccountDst(account);
    }

    @Override
    public List<Transaction> retrieveBeforeDate(Date date) {
        List<Transaction> trList = dao.findByStartLessThan(date);
        trList.addAll(dao.findByEndLessThan(date));
        return trList;
    }

    @Override
    public List<Transaction> retrieveAfterDate(Date date) {
        List<Transaction> trList = dao.findByStartGreaterThan(date);
        trList.addAll(dao.findByEndGreaterThan(date));
        return trList;
    }
}
