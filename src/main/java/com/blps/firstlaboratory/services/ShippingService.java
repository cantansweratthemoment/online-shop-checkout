package com.blps.firstlaboratory.services;

import com.blps.firstlaboratory.model.Shipping;
import com.blps.firstlaboratory.repostitory.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingService {
    private final ShippingRepository shippingRepository;

    public Shipping getShippingByCountryAndRegion(String country, String region) {
        return shippingRepository.findShippingByCountryAndRegion(country, region);
    }
}
