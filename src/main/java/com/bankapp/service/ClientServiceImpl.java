package com.bankapp.service;

import com.bankapp.dao.ClientDAO;
import com.bankapp.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDAO dao;

    @Override
    public Optional<Client> retrieveClientById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<Client> retrieveAllClients() {
        return dao.findAll();
    }

    @Override
    public Client updateClient(Client client) {
        return dao.save(client);
    }

    @Override
    public Boolean deleteClientById(Integer id) {
        dao.deleteById(id);
        return !dao.existsById(id);
    }

    @Override
    public List<Client> findByClientName(String name) {
        return dao.findByNameLike(name);
    }

    @Override
    public List<Client> findByClientAddress(String address) {
        return dao.findByAddressLike(address);
    }

    @Override
    public List<Client> findByClientAge(Integer age) {
        return dao.findByAgeGreaterThan(age);
    }
}
