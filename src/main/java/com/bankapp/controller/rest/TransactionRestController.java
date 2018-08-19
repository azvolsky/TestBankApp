package com.bankapp.controller.rest;

import com.bankapp.entity.Transaction;
import com.bankapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

public class TransactionRestController {

    @Autowired
    private TransactionService txService;


    @RequestMapping(value = "/rest/tx/{id}", method = RequestMethod.GET)
    public Optional<Transaction> getClient(@PathVariable Integer id) {
        return txService.retrieveById(id);
    }

    @RequestMapping(value = "/rest/tx", method = RequestMethod.GET)
    public List<Transaction> getClients() {
        return txService.retrieveAll();
    }

}
