package com.blps.firstlaboratory.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class SaveMessageDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.getProcessEngineServices().getRuntimeService()
                .createMessageCorrelation("savedMessage")
                .setVariable("mail", delegateExecution.getVariable("mail"))
                .setVariable("products", delegateExecution.getVariable("products"))
                .setVariable("country", delegateExecution.getVariable("country"))
                .setVariable("region", delegateExecution.getVariable("region"))
                .correlate();
    }
}
