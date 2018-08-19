package com.bankapp.controller.ui;

import com.bankapp.entity.Account;
import com.bankapp.form.AccountForm;
import com.bankapp.form.TransferForm;
import com.bankapp.service.AccountService;
import com.bankapp.service.ClientService;
import com.bankapp.service.TransactionService;
import com.bankapp.service.TransferOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class TransferController {

    @Autowired
    AccountService accountService;

    @Autowired
    ClientService clientService;

    @Autowired
    TransactionService txService;

    @Autowired
    TransferOperationService transferService;

    @RequestMapping(value = { "/transfer" }, method = RequestMethod.GET)
    public String showAddAccountPage(Map<String, Object> model) {

        TransferForm transferForm = new TransferForm();
        model.put("transferForm", transferForm);
        model.put("page", "TransferForm");
        model.put("fragment_table", "transfer_form");
        model.put("fragment_script", "sections_script");

        return "index";
    }

    @RequestMapping(value = { "/transfer" }, method = RequestMethod.POST)
    public String addAccount(Map<String, Object> model, @ModelAttribute("transferForm") TransferForm transferForm) {

        Double amount = Double.valueOf(transferForm.getAmount());

        if(transferForm.getSrcId() == null || transferForm.getSrcId().isEmpty()) {
            Integer dstId = Integer.valueOf(transferForm.getDstId());
            Account dst = accountService.retrieveAccountById(dstId).get();
            transferService.transfer(dst, amount);
        }
        else if(transferForm.getDstId() == null || transferForm.getDstId().isEmpty()) {
            Integer srcId = Integer.valueOf(transferForm.getSrcId());
            Account src = accountService.retrieveAccountById(srcId).get();
            transferService.withdraw(src, amount);
        }
        else {
            Integer srcId = Integer.valueOf(transferForm.getSrcId());
            Integer dstId = Integer.valueOf(transferForm.getDstId());
            Account src = accountService.retrieveAccountById(srcId).get();
            Account dst = accountService.retrieveAccountById(dstId).get();
            transferService.transfer(src, dst, amount);
        }
        model.put("accounts", accountService.retrieveAllAccounts());
        model.put("page", "AccountList");
        model.put("fragment_table", "account_table");
        model.put("fragment_script", "sections_script");

        return "redirect:/accountList";
    }

}
