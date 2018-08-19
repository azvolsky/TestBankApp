package com.bankapp.service;

import com.bankapp.entity.Account;
import com.bankapp.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Optional<Client> retrieveClientById(Integer id);

    List<Client> retrieveAllClients();

    Client updateClient(Client client);

    Boolean deleteClientById(Integer id);

    List<Client> findByClientName(String name);

    List<Client> findByClientAddress(String name);

    List<Client> findByClientAge(Integer age);
}
