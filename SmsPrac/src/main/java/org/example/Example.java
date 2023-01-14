package org.example;

// Install the Java helper library from twilio.com/docs/java/install

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Example {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init("AC3c2dce71dc5a0b69d1c3b247c916a6ff","d3b402c7c83fc7ec1e5347d5eb2140b4");
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("+919082123665"),
                        new com.twilio.type.PhoneNumber("+13236133257"),
                        "Hi suchit tuza phone hack zala ahe")
                .create();

        System.out.println(message.getSid());
    }
}