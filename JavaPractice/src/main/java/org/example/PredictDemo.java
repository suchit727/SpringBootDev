package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredictDemo {
    public static void main(String[] args) {


        Predicate<Integer> predicate = (t) -> {
            if (t % 2 == 0) {
                return true;
            } else {
                return false;
            }
        };
        System.out.println(predicate.test(8));
        List<Integer> list = Arrays.asList(1, 2, 3, 3, 5, 6, 7, 8);

        list.stream().filter(predicate).forEach(t -> System.out.println(t));

    }
}
