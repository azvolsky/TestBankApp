package com.bankapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, insertable = true, updatable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "AccountIdDst", referencedColumnName = "Id")
    private Account accountDst;

    @ManyToOne
    @JoinColumn(name = "AccountIdSrc", referencedColumnName = "Id")
    private Account accountSrc;

    @Column(name = "Transfer", nullable = false, insertable = true, updatable = true)
    private Double transfer;

    @Column(name = "Finished", nullable = false, insertable = true, updatable = true)
    private Boolean finished = false;

    @Column(name = "StartTX", nullable = true, insertable = true, updatable = true)
    private Date start;

    @Column(name = "EndTX", nullable = true, insertable = true, updatable = true)
    private Date end;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccountDst() {
        return accountDst;
    }

    public void setAccountDst(Account accountDest) {
        this.accountDst = accountDest;
    }

    public Account getAccountSrc() {
        return accountSrc;
    }

    public void setAccountSrc(Account accountSrc) {
        this.accountSrc = accountSrc;
    }

    public Double getTransfer() {
        return transfer;
    }

    public void setTransfer(Double transfer) {
        this.transfer = transfer;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
