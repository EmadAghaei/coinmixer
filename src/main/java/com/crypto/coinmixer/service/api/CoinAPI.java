package com.crypto.coinmixer.service.api;

import com.crypto.coinmixer.domain.Transfer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class CoinAPI {
    private final String coinUri = "http://jobcoin.gemini.com/todo_my_instance/api/";

    private final Logger LOG = LoggerFactory.getLogger(CoinAPI.class);

    @Autowired
    private ObjectMapper mapper;


    public String singleTransfer(Transfer transfer) {

        final String createPersonUrl = "http://jobcoin.gemini.com/exemplify-untagged/api/transactions";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("fromAddress", transfer.getSrcAddress());
        personJsonObject.put("toAddress", transfer.getDstAddress());
        personJsonObject.put("amount", transfer.getAmount());
        HttpEntity<String> request =
                new HttpEntity<String>(personJsonObject.toString(), headers);
        String res = new RestTemplate().postForObject(createPersonUrl, request, String.class);
        return res;
    }

    public Map<String, Object> balanceAndTransactions(String sourceId) {
        final String stringUrl="http://jobcoin.gemini.com/exemplify-untagged/api/addresses/";
        String response = new RestTemplate().getForEntity(stringUrl + sourceId, String.class).getBody();
        Map<String, Object> resultObj=null;
        try {
            resultObj = mapper.readValue(response, new TypeReference<Map<String,Object>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(resultObj);

        return resultObj;

    }

}
