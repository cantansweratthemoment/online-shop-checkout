package com.blps.firstlaboratory.requests;

import lombok.Data;

@Data
public class ProductPossibilityRequest {
    String [] productNames;
    String country;
    String region;
}
