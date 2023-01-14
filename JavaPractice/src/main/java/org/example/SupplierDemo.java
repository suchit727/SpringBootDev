package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class SupplierDemo {
    public static void main(String[] args) {
        Supplier<String> stringSupplier = () -> {
            return "hi suchit this is empty";
        };
        List<String> list = Arrays.asList("a", "b");
        List<String> list1 = Arrays.asList();
        System.out.println(list.stream().findAny().orElseGet(stringSupplier));
        System.out.println(list1.stream().findAny().orElseGet(stringSupplier));

    }

}
