package org.example.customer;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;
@Log
public class MapAndFlatMapDemo {
    public static void main(String[] args) {
        List<Customer> customers = EcartDatabase.getAll();
        // List<Customer convert List<String> -> Data Transformation
        // mapping customer -> customer.getEmail()
        // customer -> customer.getEmail() one to one mapping
        List<String> emails = customers.stream().map(customer -> customer.getEmail()).collect(Collectors.toList());
        log.info("Customer emails {} "+emails);
        // customer -> customer.getPhone Numbers() ->> one to many mapping
        // customer -> customer.getPhoneNumbers() >> one to many mapping
        List<List<String>> phoneNumbers = customers.stream().map(customer -> customer.getPhoneNumbers()).collect(Collectors.toList());
        log.info("Customer Phone Numbers {} using map "+phoneNumbers);
        // List<Customer> convert List<String> -> Data Transformation
        // mapping customer -> phone Numbers
        // customer -> customer.getPhoneNumbers() ->> one to many mapping
        List<String> phones=customers.stream().flatMap(customer -> customer.getPhoneNumbers().stream()).collect(Collectors.toList());
        log.info("Customer Phone Numbers {} using flat map "+phones);
    }
}
