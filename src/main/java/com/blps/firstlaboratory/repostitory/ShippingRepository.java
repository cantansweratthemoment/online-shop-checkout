package com.blps.firstlaboratory.repostitory;

import com.blps.firstlaboratory.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
    Shipping findShippingByCountryAndRegion(String country, String region);
}
