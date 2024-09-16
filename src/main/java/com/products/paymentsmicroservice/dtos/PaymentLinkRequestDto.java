package com.products.paymentsmicroservice.dtos;

import lombok.Data;

@Data
public class PaymentLinkRequestDto {

    long orderId;

    double amount;
}
