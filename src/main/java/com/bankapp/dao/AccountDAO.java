package com.bankapp.dao;

import com.bankapp.entity.Account;
import com.bankapp.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDAO extends CrudRepository<Account, Integer> {

    public List<Account> findByClientId(Integer id);

    public List<Account> findAll();

}