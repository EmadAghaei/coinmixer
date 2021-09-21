package com.crypto.coinmixer.stories;

import com.crypto.coinmixer.CoinmixerApplication;
import com.crypto.coinmixer.dao.TransactionDAO;
import com.crypto.coinmixer.dao.UserDAO;
import com.crypto.coinmixer.domain.HouseAddress;
import com.crypto.coinmixer.domain.Status;
import com.crypto.coinmixer.domain.Transfer;
import com.crypto.coinmixer.entity.TransactionDestinationEntity;
import com.crypto.coinmixer.entity.TransactionEntity;
import com.crypto.coinmixer.entity.UserEntity;
import com.crypto.coinmixer.service.TaskScheduledService;
import com.crypto.coinmixer.service.api.CoinAPI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CoinmixerApplication.class)
@RunWith(SpringRunner.class)
public class SimpleScenarioTest {

    private final String srcAddress = "Alice";
    private final String userId = "AliceUserId";
    private final List<String> dstAddressList = new ArrayList<>(Arrays.asList("Dest1", "Dest2", "Dest3"));
    private final String depositAddress = "Deposit1";
    private BigDecimal amount = new BigDecimal(5);
    @MockBean
    private UserDAO userDAO;

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private TransactionDAO transactionDAO;
    @Autowired
    private CoinAPI coinAPI;
    @Autowired
    private TaskScheduledService taskScheduledService;
    @Autowired
    private HouseAddress houseAddress;


    @Before
    public void init() {
        given(this.userDAO.getByUserId(userId)).willReturn(createUserObj());
    }

    //Step1: You provide a list of new, unused addresses that you own to the mixer;
    //Step2: The mixer provides you with a new deposit address that it owns;
    @Test
    public void step1And2_GetDepositAddressAndCheckUser() {
        String getAddress = "/addresses/deposit" + "?userId=" + userId + "&srcAddress=" + srcAddress + "&dstAddressList=" + dstAddressList + "&amount=" + amount;
        WebTestClient.ResponseSpec responseSpec = this.webTestClient.get().uri(getAddress).accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();
    }

    //Step3: You transfer your bitcoins to that address;

    @Test
    public void step3_transferToDeposit() {
//        They have their dedicated deposit address. The can transfer money with any app or approach.
// If they want they can also transfer the money with our app. I implemented that usecase.

//        Transfer transfer = createTransferObj(srcAddress,depositAddress,amount);
//        String uri = "/transfer/deposit";
//        this.webTestClient.post().uri(uri).body(Mono.just(transfer), Transfer.class).exchange().expectStatus().isOk();
    }

    // Step4: The mixer will detect your transfer by watching or polling the P2P Bitcoin network;
    @Test
    public void step4_pollingDeposit() {
        List<TransactionEntity> mockedTransactions = createListOfTransactionsForTest();
        given(this.transactionDAO.findAllTransactionByStatus(Status.INITIATED.name())).willReturn(mockedTransactions);

        taskScheduledService.pollingDeposits();

    }

    // Step5: The mixer will transfer your bitcoin from the deposit address into a big “house account” along with all the other bitcoin currently being mixed; and
    @Test
    public void step5_transferToHouse() {

        TransactionEntity transactionEntity = new TransactionEntity();
        given(this.userDAO.getByUserId(userId)).willReturn(createUserObj());
        given(this.transactionDAO.getTansactionByDepositAndUserId(userId, depositAddress, amount)).willReturn(transactionEntity);
        String uri = "/transfer/deposit";
        String res = coinAPI.singleTransfer(createTransferObj(depositAddress, houseAddress.getAddressId(), new BigDecimal(1)));
        assertEquals(res, "{\"status\":\"OK\"}");
    }

    // Step 6: Then, over some time the mixer will use the house account to dole out your
    // bitcoin in smaller discrete increments to the withdrawal addresses that you provided, possibly after deducting a fee.
    @Test
    public void step6_transferToDestinationsIncrementally() {
        List<TransactionEntity> mockedTransactions = createListOfTransactionsForTest();
        populateTransactionDestination(mockedTransactions);

        given(this.transactionDAO.findAllTransactionByStatus(Status.TRANSFERRED_TO_HOUSE.name())).willReturn(mockedTransactions);
        taskScheduledService.transferFromHouseToDestinations();
    }

    private Transfer createTransferObj(String src, String dst, BigDecimal amount) {
        Transfer transfer = new Transfer();
        transfer.setSrcAddress(src);
        transfer.setDstAddress(dst);
        transfer.setAmount(amount);
        transfer.setUserId(userId);
        return transfer;
    }

    private UserEntity createUserObj() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setAddressId(srcAddress);
        userEntity.setActive(true);
        return userEntity;
    }

    private List<TransactionEntity> createListOfTransactionsForTest() {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setDepositAddress(depositAddress);
        transaction.setAmount(new BigDecimal(90));
        transaction.setStatus(Status.INITIATED.name());

        TransactionEntity transaction2 = new TransactionEntity();
        transaction2.setDepositAddress("Deposit2");
        transaction2.setAmount(new BigDecimal(2));
        transaction.setStatus(Status.INITIATED.name());

        return new ArrayList<TransactionEntity>(Arrays.asList(transaction, transaction2));
    }

    private void populateTransactionDestination(List<TransactionEntity> mockedTransactions) {
        for (TransactionEntity transaction : mockedTransactions) {

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
}