package com.blps.firstlaboratory.requests;

import lombok.Data;

@Data
public class AddCustomerRequest {
    String login;
    String name;
    String mail;
}
