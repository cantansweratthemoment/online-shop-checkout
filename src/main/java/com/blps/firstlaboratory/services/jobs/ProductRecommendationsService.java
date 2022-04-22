package com.blps.firstlaboratory.services.jobs;

import com.blps.firstlaboratory.model.PopularProducts;
import com.blps.firstlaboratory.model.Product;
import com.blps.firstlaboratory.model.ProductType;
import com.blps.firstlaboratory.repostitory.PopularProductsRepository;
import com.blps.firstlaboratory.repostitory.ProductRepository;
import com.blps.firstlaboratory.repostitory.ProductTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductRecommendationsService {
    private final ProductTypeRepository productTypeRepository;
    private final PopularProductsRepository popularProductsRepository;
    private final ProductRepository productRepository;

    @Scheduled(cron = "@daily")
    public void getMostPopularProductsInEachCategory() {
        List<ProductType> productTypes = productTypeRepository.findAll();
        for (ProductType productType : productTypes) {
            PopularProducts popularProducts = new PopularProducts();
            List<Product> productsWithThisType = productRepository.findAllByProductTypeIs(productType);
            if (productsWithThisType.isEmpty()) {
                continue;
            }
            Integer max = productsWithThisType.get(0).getTimesBought();
            int maxInd = 0;
            for (int i = 0; i < productsWithThisType.size(); i++) {
                if (productsWithThisType.get(i).getTimesBought() > max) {
                    max = productsWithThisType.get(i).getTimesBought();
                    maxInd = i;
                }
            }
            popularProducts.setTime(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
            popularProducts.setType(productType);
            popularProducts.setProduct(productsWithThisType.get(maxInd));
            popularProductsRepository.save(popularProducts);
        }
    }
}
