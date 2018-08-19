package com.bankapp.service;

import com.bankapp.entity.Account;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface AccountService {

    Optional<Account> retrieveAccountById(Integer id);

    List<Account> retrieveAllAccounts();

    List<Account> retrieveAccountsByClientId(Integer id);

    Account updateAccount(Account account);

    Boolean deleteAccountById(Integer id);

    List<Account> findByClientId(Integer id);
}
