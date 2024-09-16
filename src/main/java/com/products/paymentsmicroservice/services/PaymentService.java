package com.products.paymentsmicroservice.services;


import com.stripe.exception.StripeException;

public interface PaymentService {

    public String createPaymentLink(long orderId, double amount) throws StripeException;
}
