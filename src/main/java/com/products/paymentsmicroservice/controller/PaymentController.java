package com.products.paymentsmicroservice.controller;

import com.products.paymentsmicroservice.dtos.PaymentLinkRequestDto;
import com.products.paymentsmicroservice.services.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/createLink")
    public ResponseEntity<String> createPaymentLink(@RequestBody PaymentLinkRequestDto paymentLinkRequestDto) throws StripeException {
        String paymentUrl = paymentService.createPaymentLink(paymentLinkRequestDto.getOrderId(),paymentLinkRequestDto.getAmount());
        return ResponseEntity.ok(paymentUrl);
    }

}
