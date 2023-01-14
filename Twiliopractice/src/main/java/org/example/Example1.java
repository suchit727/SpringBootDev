package org.example;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class Example1 {



        public static void main(String[] args) {
            Twilio.init(
                    System.getenv("TWILIO_ACCOUNT_SID"),
                    System.getenv("TWILIO_AUTH_TOKEN"));
            Message.creator(
                            new PhoneNumber("+919594017113"),
                            new PhoneNumber("+19496948938"),
                            "Hello from Rakesh, This message is sended from Twilio account").create();
            System.out.println("Sended succesfully");
        }
    }

