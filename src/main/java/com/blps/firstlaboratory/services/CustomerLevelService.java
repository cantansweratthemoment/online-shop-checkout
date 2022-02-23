package com.blps.firstlaboratory.services;

import com.blps.firstlaboratory.repostitory.CustomerLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerLevelService {
    private final CustomerLevelRepository customerLevelRepository;

    public Double getDiscount(Long id) {
        return customerLevelRepository.getDistinctById(id);
    }
}
