package com.crypto.coinmixer.controller;

import com.crypto.coinmixer.domain.Status;
import com.crypto.coinmixer.service.AddressService;
import com.crypto.coinmixer.service.TransactionService;
import com.crypto.coinmixer.service.UserService;
import com.crypto.coinmixer.service.api.CoinAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    Logger logger = LoggerFactory.getLogger(AddressController.class);
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CoinAPI coinAPI;


    /**
     * t check the user to be valid. Then create a unique deposit address for each user and store a transaction for the user.
     *
     * @param userId
     * @param srcAddress
     * @param dstAddressList
     * @param amount
     * @return
     */
    @GetMapping("/deposit")
    public ResponseEntity getMappedDeposit(
            @RequestParam String userId, @RequestParam String srcAddress, @RequestParam List<String> dstAddressList, @RequestParam BigDecimal amount) {
        logger.info("/getMappedDeposit service is called by userId: " + userId + " srcAddress: " + srcAddress);
        if (!userService.isValid(userId, srcAddress)) {
            logger.error("UserId" + userId + " called with wrong information");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user information is invalid.");
        }
        String depositAddress = null;
        try {
            depositAddress = addressService.getDepositAddress(userId, srcAddress, dstAddressList, amount);
            // it saves the transaction with its destinatins
            transactionService.storeInitialTransaction(userId, depositAddress, srcAddress, dstAddressList, amount, Status.INITIATED);
        } catch (InterruptedException e) {
            logger.error("UserId" + userId + " internal error");
        }
        return depositAddress != null ? ResponseEntity.ok(depositAddress) : ResponseEntity.notFound().build();
    }

    /**
     * it is wrapper on top of transfer API.
     * @param userId
     * @param address
     * @return
     */
    @GetMapping("/balanceAndHistory")
    public ResponseEntity getBalanceAndHistory(@RequestParam String userId, @RequestParam String address) {
        logger.info("/balance service is called by userId: " + userId + " srcAddress: " + address);
        if (!userService.isValid(userId, address)) {
            logger.error("UserId" + userId + " called with wrong information");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user information is invalid.");
        }
        Map<String, Object> apiResponse = coinAPI.balanceAndTransactions(address);
        if (apiResponse.equals("{\"status\":\"OK\"}")){
            return  ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

}
