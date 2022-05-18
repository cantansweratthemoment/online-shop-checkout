package com.blps.firstlaboratory.delegates;

import com.blps.firstlaboratory.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.Arrays;
import java.util.Map;

@Named
@RequiredArgsConstructor
public class ShippingPossibilityDelegate implements JavaDelegate {
    private final ProductService productService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String productsS = (String) delegateExecution.getVariable("products");
        productsS = productsS.trim();
        String[] products = productsS.split(" ");
        String country = (String) delegateExecution.getVariable("country");
        String region = (String) delegateExecution.getVariable("region");
        Map<String, Boolean> result = productService.checkPossibility(products, country, region);
        boolean is_delivery = true;
        for (Boolean i : result.values()) {
            if (!i) {
                is_delivery = false;
                break;
            }
        }
        System.out.println("VALUE ->  "  + is_delivery);
        System.out.println("VALUE ->  "  + Arrays.toString(products));
        System.out.println("VALUE ->  "  + country);
        System.out.println("VALUE ->  "  + region);

        delegateExecution.setVariable("is_delivery", is_delivery);
    }
}
