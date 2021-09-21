package com.crypto.coinmixer.dao;

import com.crypto.coinmixer.domain.Status;
import com.crypto.coinmixer.domain.Transaction;
import com.crypto.coinmixer.domain.TransactionDestination;
import com.crypto.coinmixer.domain.Transfer;
import com.crypto.coinmixer.entity.TransactionDestinationEntity;
import com.crypto.coinmixer.entity.TransactionEntity;
import com.crypto.coinmixer.entity.TransactionToDestinationDetailEntity;
import com.crypto.coinmixer.entity.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


// I used @Component to bypass DB & Hibernate configuration. I can add them later.
@Component
public class TransactionDAO implements BaseDAO<TransactionEntity>{


    public TransactionEntity getTansactionByDepositAndUserId(String userId, String depositAddress, BigDecimal amount) {
        return new TransactionEntity();// TODO: 9/20/21 Uncomment the below code after adding DB
//        Criteria crit = HibernateConfiguration.getCurrentSession().createCriteria(TransactionEntity.class);
//        crit.add(Restrictions.eq("amount",amount));
//        crit.add(Restrictions.eq("depositAddress",depositAddress));
//        crit.createCriteria("userEntity").add(Restrictions.eq("userId", userId));
//        List<TransactionEntity> results = crit.list();
//        if(results.size()>1) return null;
//        return results.get(0);
    }

    public void updateStatus(String TRANSFERRED_TO_DEPOSIT, Transfer transfer) {
        // TODO: 9/20/21 uncoment after DB
//        TransactionEntity transactionEntity = getTansactionByDepositAndUserId(transfer.getUserId(), transfer.getDstAddress(),transfer.getAmount());
//        transactionEntity.setStatus(TRANSFERRED_TO_DEPOSIT);
//        HibernateConfiguration.getCurrentSession().saveOrUpdate(transactionEntity);
    }

    public List<TransactionEntity> findAllTransactionByStatus(String status) {
        Criteria crit = HibernateConfiguration.getCurrentSession().createCriteria(TransactionEntity.class);
        crit.add(Restrictions.eq("status",status));
       return crit.list();
    }

    public void updateStatusOfTransactionToHouse(TransactionEntity transaction) {
        HibernateConfiguration.getCurrentSession().saveOrUpdate(transaction);
    }

    @Override
    public Optional<TransactionEntity> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<TransactionEntity> getAll() {
        return null;
    }

    @Override
    public void save(TransactionEntity transactionEntity) {
//        HibernateConfiguration.getCurrentSession().saveOrUpdate(transactionEntity);
    }

    public void saveAll(List<TransactionEntity> transactionList) {
        transactionList.stream().forEach(t ->save(t));
//        HibernateConfiguration.getCurrentSession().saveOrUpdate(transactionEntity);
    }

    @Override
    public void update(TransactionEntity transactionEntity, String[] params) {

    }

    @Override
    public void delete(TransactionEntity transactionEntity) {

    }

    public void saveInitialTransaction(String userId, String depositAddress, String srcAddress, List<String> dstAddressList, BigDecimal amount, Status initiated) {
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        user.setLastDepositId(depositAddress);
        TransactionEntity transaction = new TransactionEntity();
        transaction.setUserEntity(user);
        transaction.setAmount(amount);
        transaction.setStatus(initiated.name());
        transaction.setDepositAddress(depositAddress);
        transaction.setSrcAddress(srcAddress);
        transaction.setTransactionDestinationEntitySet(new HashSet<>());
        // the rest properies of transaction are null in this stage
        for (String dstAddress : dstAddressList) {
            TransactionDestinationEntity transactionDestination = new TransactionDestinationEntity();
            transactionDestination.setDestinationAddress(dstAddress);
            transactionDestination.setAmount(amount);
            transactionDestination.setStatus(initiated.name());
            transaction.getTransactionDestinationEntitySet().add(transactionDestination);
        }
        save(transaction);
    }





}
