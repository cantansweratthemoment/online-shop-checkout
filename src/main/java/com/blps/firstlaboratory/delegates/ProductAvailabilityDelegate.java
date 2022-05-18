package com.blps.firstlaboratory.delegates;

import com.blps.firstlaboratory.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class ProductAvailabilityDelegate implements JavaDelegate {
    private final ProductService productService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String productsS = (String) delegateExecution.getVariable("products");
        productsS = productsS.trim();
        String[] products = productsS.split(" ");
        productService.checkExists(products);
    }
}
