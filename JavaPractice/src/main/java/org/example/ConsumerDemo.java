package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> consumer = t -> System.out.println(t);
        consumer.accept("Happy Diwali");

        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 6);
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
//        list.stream().forEach(consumer);
        list1.stream().forEach(t -> System.out.println(t));

    }
}
