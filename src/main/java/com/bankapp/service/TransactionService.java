package com.bankapp.service;

import com.bankapp.entity.Account;
import com.bankapp.entity.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionService {

    Transaction update(Transaction tr);

    Optional<Transaction> retrieveById(Integer id);

    List<Transaction> retrieveAll();

    List<Transaction> retrieveByAccountAll(Account account);

    List<Transaction> retrieveByAccountSrc(Account account);

    List<Transaction> retrieveByAccountDst(Account account);

    List<Transaction> retrieveBeforeDate(Date date);

    List<Transaction> retrieveAfterDate(Date date);

}
