package com.birthdayemail.twilioemail.controller;

import com.birthdayemail.twilioemail.model.EmailRequest;
import com.birthdayemail.twilioemail.service.MailService;
import com.jayway.jsonpath.JsonPath;
import com.sendgrid.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Log4j2
public class MailController  {

    @Value("${topic.name.consumer")
    private String topicName;

    @Autowired
    private MailService mailService;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, JSONObject> payload) {
        log.info("Topic Name: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partition: {}", payload.partition());
        log.info("order:{}", payload.value());
//        Gson gson=new Gson();
       // JSONObject peopleInJson = payload.value();
       // People people=gson.fromJson(payload.value().toJSONString(),People.class);
        //log.info(people.getEmail_id());

        String data= String.valueOf(payload.value());
        String email= JsonPath.read(data,"$.People.email_id");
        String firstName= JsonPath.read(data,"$.People.firstName");
        String lastName= JsonPath.read(data,"$.People.lastName");
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(email);
        Date date = new Date();
        log.info("Getting list of mail id's whose birthdate is on : {}  ", date);
        mailService.setDetails(firstName,lastName);
        Response response = mailService.sendEmail(emailRequest);

        log.info("Service method inside controller has been called");
        if (response.getStatusCode() == 200 || response.getStatusCode() == 202) {
//            log.info("Your mail has been sent successfully to the email id : {} ", people.toJSONString());
//            log.info(people.entrySet().stream().toArray());
        } else {
            log.info("failed to send mail to the {}");
        }
    }


}


