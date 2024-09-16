package com.products.paymentsmicroservice.payment_gateways;

import com.stripe.exception.StripeException;

public interface PaymentGatewayAdapter {

    public String createPaymentLink(long orderId, double amount) throws StripeException;

}
