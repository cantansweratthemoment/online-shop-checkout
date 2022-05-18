package com.blps.firstlaboratory.delegates;

import com.blps.firstlaboratory.services.CustomerService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class AddCustomerDelegate implements JavaDelegate {
    private final CustomerService customerService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String login = (String) delegateExecution.getVariable("login");
        String name = (String) delegateExecution.getVariable("name");
        String mail = (String) delegateExecution.getVariable("mail");
        customerService.addCustomer(login, name, mail);
    }
}
