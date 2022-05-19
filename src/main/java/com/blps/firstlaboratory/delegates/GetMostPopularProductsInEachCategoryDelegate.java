package com.blps.firstlaboratory.delegates;

import com.blps.firstlaboratory.services.jobs.ProductRecommendationsService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class GetMostPopularProductsInEachCategoryDelegate implements JavaDelegate {
    private final ProductRecommendationsService productRecommendationsService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        productRecommendationsService.getMostPopularProductsInEachCategory();
    }
}
