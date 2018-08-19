package com.bankapp.controller.ui;

import com.bankapp.service.AccountService;
import com.bankapp.service.ClientService;
import com.bankapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class TransactionController {

    @Autowired
    AccountService accountService;

    @Autowired
    ClientService clientService;

    @Autowired
    TransactionService txService;

    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping(value = "/txList/{id}", method = RequestMethod.GET)
    public String txList(@PathVariable Integer id, Map<String, Object> model) {
        model.put("title", "Transactions");
        model.put("tx", txService.retrieveByAccountAll(accountService.retrieveAccountById(id).get()));
        model.put("page", "TxList");
        model.put("fragment_table", "tx_table");
        model.put("fragment_script", "tx_script");
        return "index";
    }

    @RequestMapping(value = "/txList", method = RequestMethod.GET)
    public String txList(Map<String, Object> model) {
        model.put("title", "Transactions");
        model.put("tx", txService.retrieveAll());
        model.put("page", "TxList");
        model.put("fragment_table", "tx_table");
        model.put("fragment_script", "tx_script");
        return "index";
    }
}
