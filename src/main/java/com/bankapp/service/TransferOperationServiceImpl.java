package com.bankapp.service;

import com.bankapp.entity.Account;
import com.bankapp.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
public class TransferOperationServiceImpl implements TransferOperationService {

    @Autowired
    private TransactionService trService;

    @Autowired
    private AccountService accountService;

    @Override
    public synchronized Boolean transfer(Account src, Account dst, final Double amount) {
        if(src==null || dst == null || amount == null)
            return false;

        if(amount > src.getAmount())
            return false;

        Transaction tx = new Transaction();
        tx.setAccountSrc(src);
        tx.setAccountDst(dst);
        tx.setFinished(false);
        tx.setTransfer(amount);
        tx.setStart(new Date());

        trService.update(tx);

        src.setAmount(src.getAmount() - amount);
        dst.setAmount(dst.getAmount() + amount);

        accountService.updateAccount(src);
        accountService.updateAccount(dst);

        tx.setFinished(true);
        tx.setEnd(new Date());

        trService.update(tx);

        return true;
    }

    @Override
    public synchronized Boolean transfer(Account dst, Double amount) {
        if(dst == null || amount == null)
            return false;

        Transaction tx = new Transaction();
        tx.setAccountDst(dst);
        tx.setFinished(false);
        tx.setTransfer(amount);
        tx.setStart(new Date());

        trService.update(tx);

        dst.setAmount(dst.getAmount() + amount);

        accountService.updateAccount(dst);

        tx.setFinished(true);
        tx.setEnd(new Date());

        trService.update(tx);

        return true;
    }

    @Override
    public synchronized Boolean withdraw(Account src, Double amount) {
        Boolean result = true;

        if(src==null || amount == null)
            return false;

        if(amount > src.getAmount())
            return false;

        Transaction tx = new Transaction();
        tx.setAccountSrc(src);
        tx.setFinished(false);
        tx.setTransfer(amount);
        tx.setStart(new Date());

        trService.update(tx);

        src.setAmount(src.getAmount() - amount);

        accountService.updateAccount(src);

        tx.setFinished(true);
        tx.setEnd(new Date());

        trService.update(tx);

        return true;
    }
}
