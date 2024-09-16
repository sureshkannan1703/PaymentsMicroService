package com.products.paymentsmicroservice.payment_gateways;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.json.JSONObject;

@Component
@Primary
public class RazorPayPGAdapter implements PaymentGatewayAdapter {

    RazorpayClient razorpayClient;

    @Autowired
    public RazorPayPGAdapter(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String createPaymentLink(long orderId, double amount) {

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",3400);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",false);
        paymentLinkRequest.put("reference_id","#aasasw8");
        paymentLinkRequest.put("description","Payment for policy no #23456");

        JSONObject customer = new JSONObject();
        customer.put("name","Gaurav Kumar");
        customer.put("contact","+919000090000");
        customer.put("email","gaurav.kumar@example.com");
        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);

        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://scaler.com/");
        paymentLinkRequest.put("callback_method","get");

        try {
            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
        return paymentLinkRequest.get("short_url").toString();
    }
}
