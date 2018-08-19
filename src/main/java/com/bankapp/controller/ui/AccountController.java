package com.bankapp.controller.ui;

import com.bankapp.entity.Account;
import com.bankapp.entity.Client;
import com.bankapp.form.AccountForm;
import com.bankapp.form.ClientForm;
import com.bankapp.service.AccountService;
import com.bankapp.service.ClientService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    ClientService clientService;

    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping(value = "/accountList/{id}", method = RequestMethod.GET)
    public String accountList(@PathVariable Integer id, Map<String, Object> model) {
        List<Account> accounts = accountService.retrieveAccountsByClientId(id);
        model.put("accounts", accounts);
//        if(accounts.size() > 1)
        model.put("total", accounts.stream().map(Account::getAmount).reduce((aDouble1, aDouble2) -> aDouble1 + aDouble2).orElse(0D));
        model.put("page", "AccountList");
        model.put("fragment_table", "account_table");
        model.put("fragment_script", "sections_script");
//        return "AccountList";
        return "index";
    }

    @RequestMapping(value = "/accountList", method = RequestMethod.GET)
    public String accountList(Map<String, Object> model) {
        model.put("accounts", accountService.retrieveAllAccounts());
        model.put("page", "AccountList");
        model.put("fragment_table", "account_table");
        model.put("fragment_script", "sections_script");
//        return "AccountList";
        return "index";
    }

    @RequestMapping(value = { "/addAccount" }, method = RequestMethod.GET)
    public String showAddAccountPage(Map<String, Object> model) {

        AccountForm accountForm = new AccountForm();
        model.put("accountForm", accountForm);
        model.put("page", "AddAccountForm");
        model.put("fragment_table", "account_form");
        model.put("fragment_script", "sections_script");

        return "index";
    }

    @RequestMapping(value = { "/addAccount" }, method = RequestMethod.POST)
    public String addAccount(Map<String, Object> model, @ModelAttribute("accountForm") AccountForm accountForm) {

        Double amount = accountForm.getAmount();
        Integer clientId = accountForm.getClientId();

        Account account = new Account();
        account.setClient(clientService.retrieveClientById(clientId).get());
        account.setAmount(amount);

        accountService.updateAccount(account);

        return "redirect:/accountList";
    }
}
