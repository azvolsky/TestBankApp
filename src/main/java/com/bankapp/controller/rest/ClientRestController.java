package com.bankapp.controller.rest;

import com.bankapp.entity.Client;
import com.bankapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/rest/client/{name}", method = RequestMethod.GET)
    public List<Client> getClient(@PathVariable String name) {
        return clientService.findByClientName(name);
    }

    @RequestMapping(value = "/rest/client/{id}", method = RequestMethod.GET)
    public Optional<Client> getClient(@PathVariable Integer id) {
        return clientService.retrieveClientById(id);
    }

    @RequestMapping(value = "/rest/clients", method = RequestMethod.GET)
    public List<Client> getClients() {
        return clientService.retrieveAllClients();
    }
}
