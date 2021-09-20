package com.crypto.coinmixer.controller;

import com.crypto.coinmixer.service.api.CoinAPI;
import com.crypto.coinmixer.domain.Transfer;
import com.crypto.coinmixer.service.TransactionService;
import com.crypto.coinmixer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    Logger logger = LoggerFactory.getLogger(TransferController.class);
    @Autowired
    UserService userService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    CoinAPI coinAPI;

    @PostMapping("/deposit")
    public ResponseEntity transfer(@RequestBody Transfer transfer) {
        if (!userService.isValid(transfer.getUserId(), transfer.getSrcAddress()) || !transactionService.checkDepositAddressConsistency(transfer)) {
            logger.error("UserId" + transfer.getUserId() + " called with wrong information.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user information is invalid.");
        }

        String apiResponse = coinAPI.singleTransfer(transfer);
        if (apiResponse.equals("{\"status\":\"OK\"}")){
            //update status transaction in database
            transactionService.updateStatusOfTransactionToDeposit(transfer);
            return  ResponseEntity.ok("Transfer completed Successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);

    }
}
