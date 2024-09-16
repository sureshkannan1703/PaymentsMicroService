package com.products.paymentsmicroservice.services;

import com.products.paymentsmicroservice.payment_gateways.PaymentGatewayAdapter;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    PaymentGatewayAdapter paymentGatewayAdapter;

    @Autowired
    public PaymentServiceImpl(PaymentGatewayAdapter paymentGatewayAdapter) {
        this.paymentGatewayAdapter  = paymentGatewayAdapter;
    }

    @Override
    public String createPaymentLink(long orderId, double amount) throws StripeException {
        return paymentGatewayAdapter.createPaymentLink(orderId, amount);
    }
}
