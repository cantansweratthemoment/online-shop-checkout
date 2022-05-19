package com.blps.firstlaboratory.delegates;

import com.blps.firstlaboratory.services.CheckPaymentService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.ResponseEntity;

import javax.inject.Named;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Named
@RequiredArgsConstructor
public class CheckPaymentDelegate implements JavaDelegate {
    private final CheckPaymentService checkPaymentService;
    private IdentityService identityService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String login = (String) delegateExecution.getVariable("login");
        String productsS = (String) delegateExecution.getVariable("products");
        productsS = productsS.trim();
        String[] products = productsS.split(" ");
        String country = (String) delegateExecution.getVariable("country");
        String region = (String) delegateExecution.getVariable("region");
        boolean admin = identityService.getCurrentAuthentication().getGroupIds().contains("camunda-admin");
        ResponseEntity<String> result = null;
        System.out.println("VALUEV = " + admin);
        if (admin) {
            result = checkPaymentService.checkAdminPayment(products, login, country, region);
        } else {
            result = checkPaymentService.checkPayment(products, login, country, region);
        }
        if (Objects.requireNonNull(result.getBody()).equals("Payment successful!")) {
            delegateExecution.setVariable("is_payment", true);
        } else {
            delegateExecution.setVariable("is_payment", false);
        }
    }
}
