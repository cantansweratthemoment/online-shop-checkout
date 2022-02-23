package com.blps.firstlaboratory.requests;

import lombok.Data;

@Data
public class CheckPaymentRequest {
    String login;
    String[] products;
    String country;
    String region;
}
