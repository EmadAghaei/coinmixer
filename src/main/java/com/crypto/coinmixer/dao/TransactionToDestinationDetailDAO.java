package com.crypto.coinmixer.dao;

import com.crypto.coinmixer.domain.Status;
import com.crypto.coinmixer.entity.TransactionDestinationEntity;
import com.crypto.coinmixer.entity.TransactionToDestinationDetailEntity;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionToDestinationDetailDAO implements BaseDAO<TransactionToDestinationDetailEntity> {
    @Override
    public Optional<TransactionToDestinationDetailEntity> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<TransactionToDestinationDetailEntity> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public void save(TransactionToDestinationDetailEntity transactionToDestinationDetailEntity) {
//        HibernateConfiguration.getCurrentSession().saveOrUpdate(transactionToDestinationDetailEntity);
    }

    @Override
    public void update(TransactionToDestinationDetailEntity transactionToDestinationDetailEntity, String[] params) {

    }

    @Override
    public void delete(TransactionToDestinationDetailEntity transactionToDestinationDetailEntity) {

    }

    public void updateDestinationsDetails(TransactionDestinationEntity transactionDestination, BigDecimal amount) {
        TransactionToDestinationDetailEntity transactionToDestinationDetailEntity = new TransactionToDestinationDetailEntity();
        transactionToDestinationDetailEntity.setStatus(Status.COMPLETED.name());
        transactionToDestinationDetailEntity.setAmount(amount);// we save the transefred Amount
        transactionToDestinationDetailEntity.setDestinationAddress(transactionDestination.getDestinationAddress());
        transactionToDestinationDetailEntity.setTransactionDestinationEntity(transactionDestination);
        save(transactionToDestinationDetailEntity);
    }
}
