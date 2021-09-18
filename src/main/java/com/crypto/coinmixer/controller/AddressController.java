package com.crypto.coinmixer.controller;

import com.crypto.coinmixer.service.AddressService;
import com.crypto.coinmixer.service.UserService;
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

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;


    @GetMapping("/getMappedDeposit")
    public ResponseEntity getMappedDeposit(
            @RequestParam Long userId, @RequestParam String srcAddress, @RequestParam List<String> dstAddress, @RequestParam BigDecimal amount) {
        if (!userService.isValid(userId, srcAddress))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user information is wrong");
        String depositAddress = addressService.getDepositAddress(userId, srcAddress, dstAddress,amount);
        return depositAddress != null ? ResponseEntity.ok(depositAddress) : ResponseEntity.notFound().build();
    }
}
