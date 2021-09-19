package com.crypto.coinmixer.controller;

import com.crypto.coinmixer.service.AddressService;
import com.crypto.coinmixer.service.UserService;
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

@RestController
@RequestMapping("/addresses")
public class AddressController {
    Logger logger = LoggerFactory.getLogger(AddressController.class);
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Hello";
    }

    @GetMapping("/getMappedDeposit")
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
        } catch (InterruptedException e) {
            logger.error("UserId" + userId + " internal error");
        }
        return depositAddress != null ? ResponseEntity.ok(depositAddress) : ResponseEntity.notFound().build();
    }
}
