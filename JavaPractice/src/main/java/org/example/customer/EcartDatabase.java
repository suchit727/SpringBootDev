package org.example.customer;

import org.example.customer.Customer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EcartDatabase {
    public static List<Customer> getAll() {
        return Stream.of(new Customer(101, "suchit", "suchit@gmail.com", Arrays.asList("9594017113", "9082123665")),
                new Customer(102, "prem", "prem@gmail.com", Arrays.asList("9833272403", "8092123665")),
                new Customer(103, "kunal", "kunal@gmail.com", Arrays.asList("93689378388", "9082427665")),
                new Customer(104, "vaibhav", "vaibhav@gmail.com", Arrays.asList("934663732", "945256828")),
                new Customer(105, "rakesh", "rakesh@gmail.com", Arrays.asList("95452627113", "9052563665")),
                new Customer(106, "uttam", "uttam@gmail.com", Arrays.asList("9594552277", "904636732")),
                new Customer(107, "raj", "raj@gmail.com", Arrays.asList("955536638", "9084426389")),
                new Customer(108, "sanket", "sanket@gmail.com", Arrays.asList("9555377484", "9082646745"))).collect(Collectors.toList());
    }
}
