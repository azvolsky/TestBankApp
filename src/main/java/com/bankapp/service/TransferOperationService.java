package com.bankapp.service;

import com.bankapp.entity.Account;

public interface TransferOperationService {

    Boolean transfer(Account scr, Account dst, Double amount);

    Boolean transfer(Account dst, Double amount);

    Boolean withdraw(Account dst, Double amount);

}
