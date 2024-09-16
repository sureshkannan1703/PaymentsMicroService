package com.products.paymentsmicroservice.payment_gateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import com.stripe.model.PaymentLink;


@Component
public class StripePGAdapter implements PaymentGatewayAdapter{

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Override
    public String createPaymentLink(long orderId, double amount) {

        Stripe.apiKey = stripeSecretKey;
        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(1000L)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();

        Price price = null;
        try {
            price = Price.create(params);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

        String priceId = price.getId();
        PaymentLinkCreateParams paymentLinkCreateParams = PaymentLinkCreateParams.builder().
                addLineItem(
                        PaymentLinkCreateParams.LineItem.builder().
                                setPrice(priceId).setQuantity(1L).build()
                ).setAfterCompletion(
                        PaymentLinkCreateParams.AfterCompletion.builder().
                                setRedirect(
                                        PaymentLinkCreateParams.AfterCompletion.Redirect.builder().
                                                setUrl("https://www.javatpoint.com/collections-in-java").build()
                                ).setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT).build()
                ).build();
        PaymentLink paymentLink = null;
        try {
            paymentLink = PaymentLink.create(paymentLinkCreateParams);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
        return paymentLink.getUrl();
    }
}
