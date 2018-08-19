package com.bankapp.dao;

import com.bankapp.entity.Account;
import com.bankapp.entity.Client;
import com.bankapp.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionDAO extends CrudRepository<Transaction, Integer> {

    List<Transaction> findByFinished(Boolean finished);

    List<Transaction> findAll();

    List<Transaction> findByAccountDst(Account account);

    List<Transaction> findByAccountSrc(Account account);

    List<Transaction> findByStartGreaterThan(Date date);

    List<Transaction> findByStartLessThan(Date date);

    List<Transaction> findByStart(Date date);

    List<Transaction> findByEndGreaterThan(Date date);

    List<Transaction> findByEndLessThan(Date date);

    List<Transaction> findByEnd(Date date);

}