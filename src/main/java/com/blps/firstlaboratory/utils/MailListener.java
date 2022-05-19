package com.blps.firstlaboratory.utils;

import com.blps.firstlaboratory.config.MailConfig;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Date;

@Named
@RequiredArgsConstructor
public class MailListener implements JavaDelegate {

    @Value("${mail.login}")
    String MY_EMAIL;

    @Autowired
    MailConfig mailConfig;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String[] products = ((String) delegateExecution.getVariable("products")).split(" ");
        String country = (String) delegateExecution.getVariable("country");
        String region = (String) delegateExecution.getVariable("region");
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(products).forEach(product -> {
            stringBuilder.append(product).append("\n");
        });


        Message msg = new MimeMessage(mailConfig.getSession());
        msg.setFrom(new InternetAddress(MY_EMAIL));
        InternetAddress[] addresses = {new InternetAddress((String) delegateExecution.getVariable("mail"))};
        msg.setRecipients(Message.RecipientType.TO, addresses);
        msg.setSubject("Thanks for the purchase!");
        msg.setSentDate(new Date());
        msg.setText("You have recently placed an order on this website.\nYour products:\n" + stringBuilder.toString() + "\nYour order will be delivered to " + country + ", " + region + ".\n" + "Thank you for staying with us!");
        Transport.send(msg);
    }
}
