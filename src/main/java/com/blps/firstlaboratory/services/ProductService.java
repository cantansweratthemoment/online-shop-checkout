package com.blps.firstlaboratory.services;

import com.blps.firstlaboratory.model.Product;
import com.blps.firstlaboratory.model.Shipping;
import com.blps.firstlaboratory.repostitory.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Map<String, Boolean> checkExists(String[] products) {
        Map<String, Boolean> result = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        Arrays.stream(products).forEach(productName -> {
            if (!count.containsKey(productName)) {
                count.put(productName, 1);
            } else {
                count.put(productName, count.get(productName) + 1);
            }
        });

        count.forEach((name, cnt) -> {
            if (productRepository.existsByProductName(name) && productRepository.findByProductName(name).getQuantity() >= cnt) {
                result.put(name, true);
            } else {
                result.put(name, false);
            }
        });

        return result;
    }


    public Map<String, Boolean> checkPossibility(String[] products, String country, String region) {
        Map<String, Boolean> result = new HashMap<>();
        Map<String, List<Shipping>> shippingList = new HashMap<>();

        //TODO: выкинуть ошибку если нет такого продукта
        Arrays.stream(products).distinct().forEach(productName -> {
            if (productRepository.existsByProductName(productName)) {
                shippingList.put(productName, productRepository.findByProductName(productName).getShippingList());
            } else {
                shippingList.put(productName, null);
            }
        });

        shippingList.forEach((productName, list) -> {
            if (list == null) {
                result.put(productName, false);
            } else {
                AtomicBoolean flag = new AtomicBoolean(false);
                list.forEach(x -> {
                    if (x.getCountry().equals(country) && x.getRegion().equals(region)) {
                        result.put(productName, true);
                        flag.set(true);
                    } else {
                        if(!flag.get()){
                            result.put(productName, false);
                        }
                    }
                });
            }
        });

        return result;
    }

    public List<Product> getProductsByNames(String[] products) {
        return Arrays.stream(products).map(productRepository::findByProductName).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public void reduceQuantity(List<Product> products) {
        products.forEach(product -> {
            product.setQuantity(product.getQuantity() - 1);
        });
    }
}