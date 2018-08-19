package com.bankapp.dao;

import com.bankapp.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDAO extends CrudRepository<Client, Integer> {

    public List<Client> findByNameLike(String name);

    public List<Client> findByAgeGreaterThan(Integer age);

    public List<Client> findAll();

    public List<Client> findByAddressLike(String address);

}
