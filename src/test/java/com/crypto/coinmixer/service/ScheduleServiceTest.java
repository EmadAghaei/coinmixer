package com.crypto.coinmixer.service;

import com.crypto.coinmixer.CoinmixerApplication;
import com.crypto.coinmixer.dao.TransactionDAO;
import com.crypto.coinmixer.dao.TransactionToDestinationDetailDAO;
import com.crypto.coinmixer.dao.UserDAO;
import com.crypto.coinmixer.domain.Status;
import com.crypto.coinmixer.domain.TransactionDestination;
import com.crypto.coinmixer.entity.TransactionDestinationEntity;
import com.crypto.coinmixer.entity.TransactionEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CoinmixerApplication.class)
@RunWith(SpringRunner.class)
public class ScheduleServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private TransactionToDestinationDetailDAO detailDAO;
    @MockBean
    private TransactionDAO transactionDAO;
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private TaskScheduledService taskScheduledService;

    @Test
    public void transferFromDepositHouseTest() throws Exception {
        List<TransactionEntity> mockedTransactions = createListOfTransactionsForTest();
        given(this.transactionDAO.findAllTransactionByStatus(Status.TRANSFERRED_TO_DEPOSIT.name())).willReturn(mockedTransactions);
        taskScheduledService.transferFromDepositToHouseAddress();
    }

    @Test
    public void transferFromHouseToDestinationsTest() throws Exception {
        List<TransactionEntity> mockedTransactions = createListOfTransactionsForTest();
        populateTransactionDestination(mockedTransactions);

        given(this.transactionDAO.findAllTransactionByStatus(Status.TRANSFERRED_TO_HOUSE.name())).willReturn(mockedTransactions);
        taskScheduledService.transferFromHouseToDestinations();
    }

    private void populateTransactionDestination(List<TransactionEntity> mockedTransactions) {
        for(TransactionEntity transaction: mockedTransactions){


            TransactionDestinationEntity transactionDestinationEntity = new TransactionDestinationEntity();
            transactionDestinationEntity.setAmount(new BigDecimal(1));
            transactionDestinationEntity.setDestinationAddress("Dest1");
            transactionDestinationEntity.setFee(new BigDecimal(1));

            TransactionDestinationEntity transactionDestinationEntity2 = new TransactionDestinationEntity();
            transactionDestinationEntity2.setAmount(new BigDecimal(2));
            transactionDestinationEntity2.setDestinationAddress("Dest2");
            transactionDestinationEntity2.setFee(new BigDecimal(2));

            transaction.setTransactionDestinationEntitySet(new HashSet<>());
            transaction.getTransactionDestinationEntitySet().add(transactionDestinationEntity);
            transaction.getTransactionDestinationEntitySet().add(transactionDestinationEntity2);
        }
    }

    private List<TransactionEntity> createListOfTransactionsForTest() {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setDepositAddress("Deposit1");
        transaction.setAmount(new BigDecimal(1));

        TransactionEntity transaction2 = new TransactionEntity();
        transaction2.setDepositAddress("Deposit2");
        transaction2.setAmount(new BigDecimal(2));

        return new ArrayList<TransactionEntity>(Arrays.asList(transaction,transaction2));
    }

}
