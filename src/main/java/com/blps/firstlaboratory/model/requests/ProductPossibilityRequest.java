package com.blps.firstlaboratory.model.requests;

import lombok.Data;

@Data
public class ProductPossibilityRequest {
    String [] productNames;
    String country;
    String region;
}
