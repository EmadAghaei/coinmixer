package com.crypto.coinmixer.stories;

import com.crypto.coinmixer.CoinmixerApplication;
import com.crypto.coinmixer.dao.TransactionDAO;
import com.crypto.coinmixer.dao.UserDAO;
import com.crypto.coinmixer.domain.Transfer;
import com.crypto.coinmixer.entity.TransactionEntity;
import com.crypto.coinmixer.entity.UserEntity;
import com.crypto.coinmixer.service.api.CoinAPI;
import org.assertj.core.api.AbstractDoubleAssert;
import org.assertj.core.api.ObjectAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CoinmixerApplication.class)
@RunWith(SpringRunner.class)
public class SimpleScenarioTest {

    private final String srcAddress = "Alice";
    private final String userId = "AliceUserId";
    private final List<String> dstAddressList = new ArrayList<>(Arrays.asList("Dest1", "Dest2", "Dest3"));
    private BigDecimal amount = new BigDecimal(5);
    private final String depositAddress = "Deposit1";

    @MockBean
    private UserDAO userDAO;

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private TransactionDAO transactionDAO;
    @Autowired
    private CoinAPI coinAPI;


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
//        TransactionEntity transactionEntity= new TransactionEntity();
//        given(this.transactionDAO.getTansactionByDepositAndUserId(userId,"Bob",amount)).willReturn(transactionEntity);
//        Map<String, Object> obj = coinAPI.balanceAndTransactions(depositAddress);
//        Object depositBalanceBeforeTransfer =  obj.get("balance");
        Transfer transfer = createTransferObj(srcAddress,depositAddress,amount);
        String uri = "/transfer/deposit";
        this.webTestClient.post().uri(uri).body(Mono.just(transfer), Transfer.class).exchange().expectStatus().isOk();
//        Map<String, Object> obj2 = coinAPI.balanceAndTransactions(depositAddress);
//        Object depositBalanceAfterTransfer = obj2.get("balance");


    }

    private Transfer createTransferObj(String src,String dst, BigDecimal amount) {
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
}