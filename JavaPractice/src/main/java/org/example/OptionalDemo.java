package org.example;

import org.example.customer.Customer;

import java.util.Arrays;
import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Customer customer=new Customer(101, "suchit", null, Arrays.asList("9594017113", "9082123665"));
        //of
        //empty
        //ofNullable
        Optional<Object> optionalObject=Optional.empty();
        System.out.println(optionalObject);

        Optional<String> emailOptional=Optional.ofNullable(customer.getEmail());
        System.out.println(emailOptional);
        if (emailOptional.isPresent()){
            System.out.println(emailOptional.get());
        }
        System.out.println(emailOptional.orElse("suchit@1234"));
        System.out.println(emailOptional.map(String::toUpperCase).orElseGet(()->"default email"));
        System.out.println(emailOptional.orElseThrow(()->new IllegalArgumentException("no email found")));
    }
}
