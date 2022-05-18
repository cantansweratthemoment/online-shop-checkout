package com.blps.firstlaboratory.delegates;

import com.blps.firstlaboratory.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.Map;

@Named
@RequiredArgsConstructor
public class ProductAvailabilityDelegate implements JavaDelegate {
    private final ProductService productService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String productsS = (String) delegateExecution.getVariable("products");
        productsS = productsS.trim();
        String[] products = productsS.split(" ");
        Map<String, Boolean> result = productService.checkExists(products);
        boolean is_available = true;
        for (Boolean i : result.values()) {
            if (!i) {
                is_available = false;
                break;
            }
        }
        delegateExecution.setVariable("is_available", is_available);
    }
}
