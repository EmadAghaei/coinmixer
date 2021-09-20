package com.crypto.coinmixer.dao;

import com.crypto.coinmixer.domain.Status;
import com.crypto.coinmixer.domain.Transaction;
import com.crypto.coinmixer.domain.Transfer;
import com.crypto.coinmixer.entity.TransactionEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


// I used @Component to bypass DB & Hibernate configuration. I can add them later.
@Component
public class TransactionDAO implements BaseDAO{


    @Override
    public Optional get(Long id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void update(Object o, String[] params) {

    }

    @Override
    public void delete(Object o) {

    }

    public TransactionEntity getTansactionByDepositAndUserId(String userId, String depositAddress, BigDecimal amount) {
        Criteria crit = HibernateConfiguration.getCurrentSession().createCriteria(TransactionEntity.class);
        crit.add(Restrictions.eq("amount",amount));
        crit.add(Restrictions.eq("depositAddress",depositAddress));
        crit.createCriteria("userEntity").add(Restrictions.eq("userId", userId));
        List<TransactionEntity> results = crit.list();
        if(results.size()>1) return null;
        return results.get(0);
    }

    public void updateStatus(String TRANSFERRED_TO_DEPOSIT, Transfer transfer) {
        TransactionEntity transactionEntity = getTansactionByDepositAndUserId(transfer.getUserId(), transfer.getDstAddress(),transfer.getAmount());
        transactionEntity.setStatus(TRANSFERRED_TO_DEPOSIT);
        HibernateConfiguration.getCurrentSession().saveOrUpdate(transactionEntity);
    }

    public List<Transaction> findAllTransactionByStatus(String status) {
        Criteria crit = HibernateConfiguration.getCurrentSession().createCriteria(TransactionEntity.class);
        crit.add(Restrictions.eq("status",status));
       return crit.list();
    }

    public void updateStatusOfTransactionToHouse(Transaction transaction) {

        HibernateConfiguration.getCurrentSession().saveOrUpdate(transaction);
    }
}
