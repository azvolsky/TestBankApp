package com.bankapp.controller.ui;

import com.bankapp.entity.Client;
import com.bankapp.form.ClientForm;
import com.bankapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class ClientController {
    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @Autowired
    ClientService clientService;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
//        model.put("message", this.message);
//        model.put("page", "ClientList");
//        model.put("fragment_table", "client_table");
//        model.put("fragment_script", "sections_script");
        return clientsList(model);
    }

    @RequestMapping("/clientList")
    public String clientsList(Map<String, Object> model) {
        model.put("message", this.message);
        model.put("clients", clientService.retrieveAllClients());
        model.put("page", "ClientList");
        model.put("fragment_table", "client_table");
        model.put("fragment_script", "sections_script");
        return "index";
    }

    @RequestMapping(value = { "/addClient" }, method = RequestMethod.GET)
    public String showAddPersonPage(Map<String, Object> model) {

        ClientForm clientForm = new ClientForm();
        model.put("clientForm", clientForm);
        model.put("page", "AddClientForm");
        model.put("fragment_table", "client_form");
        model.put("fragment_script", "sections_script");

        return "index";
    }

    @RequestMapping(value = { "/addClient" }, method = RequestMethod.POST)
    public String savePerson(Map<String, Object> model, @ModelAttribute("clientForm") ClientForm clientForm) {

        String name = clientForm.getName();
        String address = clientForm.getAddress();
        String age = clientForm.getAge();

        Client client = new Client();
        client.setName(name);
        client.setAddress(address);
        client.setAge(Integer.valueOf(age));

        clientService.updateClient(client);

        model.put("clients", clientService.retrieveAllClients());
        model.put("page", "ClientList");
        model.put("fragment_table", "client_table");
        model.put("fragment_script", "sections_script");

        return "redirect:/clientList";
    }
}
