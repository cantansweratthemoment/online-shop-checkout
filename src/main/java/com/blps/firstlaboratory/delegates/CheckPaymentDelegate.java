package com.blps.firstlaboratory.delegates;

import com.blps.firstlaboratory.services.CheckPaymentService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class CheckPaymentDelegate implements JavaDelegate {
    private final CheckPaymentService checkPaymentService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String login = (String) delegateExecution.getVariable("login");
        String productsS = (String) delegateExecution.getVariable("products");
        productsS = productsS.trim();
        String[] products = productsS.split(" ");
        String country = (String) delegateExecution.getVariable("country");
        String region = (String) delegateExecution.getVariable("region");
        checkPaymentService.checkPayment(products, login, country, region);
    }
}
