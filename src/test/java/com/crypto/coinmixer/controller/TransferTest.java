package com.crypto.coinmixer.controller;

import com.crypto.coinmixer.CoinmixerApplication;
import com.crypto.coinmixer.dao.TransactionDAO;
import com.crypto.coinmixer.dao.UserDAO;
import com.crypto.coinmixer.domain.Transfer;
import com.crypto.coinmixer.entity.TransactionEntity;
import com.crypto.coinmixer.entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CoinmixerApplication.class)
@RunWith(SpringRunner.class)
public class TransferTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private UserDAO userDAO;
    @MockBean
    private TransactionDAO transactionDAO;
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private Transfer transfer;

    private HttpEntity<String> getStringHttpEntity(Object object) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonMeterData = mapper.writeValueAsString(object);
        return (HttpEntity<String>) new HttpEntity(jsonMeterData, headers);
    }

    @Test
    public void transferToDepositAddressTest() throws Exception {
        String userId = "emadId";
        String srcAddress = "12";
        BigDecimal amount = new BigDecimal(1);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setAddressId(srcAddress);
        userEntity.setActive(true);

        TransactionEntity transactionEntity= new TransactionEntity();

        given(this.userDAO.getByUserId(userId)).willReturn(userEntity);
        given(this.transactionDAO.getTansactionByDepositAndUserId(userId,"Bob",amount)).willReturn(transactionEntity);

        String uri = "/transfer/deposit";

        transfer.setSrcAddress(srcAddress);
        transfer.setDstAddress("Bob");
        transfer.setAmount(amount);
        transfer.setUserId(userId);
        this.webTestClient
                .post()
                .uri(uri)
                .body(Mono.just(transfer),Transfer.class)
                .exchange()
                .expectStatus().isOk();
    }
}
