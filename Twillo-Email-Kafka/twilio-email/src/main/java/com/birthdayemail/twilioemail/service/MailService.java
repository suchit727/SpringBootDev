package com.birthdayemail.twilioemail.service;

import com.birthdayemail.twilioemail.model.EmailRequest;
import com.sendgrid.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Log4j2
public class MailService {
    private  String firstName;
    private String lastName;

    @Autowired
    private final SendGrid sendGrid;

    public void setDetails( String firstName, String lastName) {
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public MailService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public Response sendEmail(EmailRequest emailRequest) {
        Mail mail = new Mail(new Email("kunal.dinkar@gmail.com"), "Birthday Wishes", new Email(emailRequest.getTo()), new Content("text/plain", "Dear "+this.firstName+" "+this.lastName+" ,"+"\n Sent from Twilio :-)"));
        log.info(this.firstName+" "+this.lastName+"trial log");
        mail.setReplyTo(new Email("kunal.dinkar@gmail.com"));
        Request request = new Request();
        Response response = new Response();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = this.sendGrid.api(request);

        } catch (IOException exception) {

            log.info(exception.getMessage());
        }
        log.info("Mail Service method has been called.");
        return response;
    }


}
