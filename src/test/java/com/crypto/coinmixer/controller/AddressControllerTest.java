package com.crypto.coinmixer.controller;

import com.crypto.coinmixer.CoinmixerApplication;
import com.crypto.coinmixer.dao.UserDAO;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CoinmixerApplication.class)
@RunWith(SpringRunner.class)
public class AddressControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private UserDAO userDAO;
    @Autowired
    private WebTestClient webTestClient;


    /*
    Helper method
     */
    private HttpEntity<String> getStringHttpEntity(Object object) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonMeterData = mapper.writeValueAsString(object);
        return (HttpEntity<String>) new HttpEntity(jsonMeterData, headers);
    }

    @Test
    public void welcomeTest() {
        this.webTestClient
                .get()
                .uri("/addresses/welcome")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void createDepositAddressAndStoreTransactions() throws Exception {
        String userId = "emadId";
        String srcAddress = "emadAddress";
        List<String> dstAddressList = new ArrayList<>(Arrays.asList("david", "thomas"));
        BigDecimal amount = new BigDecimal(10);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setAddressId(srcAddress);
        userEntity.setActive(true);

        given(this.userDAO.getByUserId(userId)).willReturn(userEntity);

        String getAddress = "/addresses/deposit" + "?userId=" + userId + "&srcAddress=" + srcAddress + "&dstAddressList=" + dstAddressList + "&amount=" + amount;
        this.webTestClient
                .get()
                .uri(getAddress)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();


    }

    @Test
    public void getBalanceAndHistoryTest() throws Exception {
        String userId = "emadId";
        String srcAddress = "emadAddress";
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setAddressId(srcAddress);
        userEntity.setActive(true);
        given(this.userDAO.getByUserId(userId)).willReturn(userEntity);

        String getAddress = "/addresses/balanceAndHistory" + "?userId=" + userId + "&srcAddress=" + srcAddress;
        this.webTestClient
                .get()
                .uri(getAddress)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

}
