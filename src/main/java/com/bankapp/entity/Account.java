package com.bankapp.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ACCOUNTS")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, insertable = true, updatable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ClientId", referencedColumnName = "Id")
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountDst")
    private Set<Transaction> transactionsDst = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountSrc")
    private Set<Transaction> transactionsSrc = new HashSet<>();

    @Column(name = "Amount")
    private Double amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Transaction> getTransactionsDst() {
        return transactionsDst;
    }

    public void setTransactionsDst(Set<Transaction> transactionsDst) {
        this.transactionsDst = transactionsDst;
    }

    public Set<Transaction> getTransactionsSrc() {
        return transactionsSrc;
    }

    public void setTransactionsSrc(Set<Transaction> transactionsSrc) {
        this.transactionsSrc = transactionsSrc;
    }
}
