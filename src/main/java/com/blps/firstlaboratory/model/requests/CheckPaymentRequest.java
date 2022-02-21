package com.blps.firstlaboratory.model.requests;

import lombok.Data;

@Data
public class CheckPaymentRequest {
    Long price;
    String login;
    String[] products;
    String country;
    String region;
}
