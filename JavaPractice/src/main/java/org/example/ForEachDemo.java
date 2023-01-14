package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ForEachDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("suchit");
        list.add("kunal");
        list.add("Raj");
        list.add("premt");
        list.add("sanket");
        list.add("rakesh");
        list.add("uttam");
        list.add("vaibhav");
        list.stream().forEach(t -> System.out.println(t));
        list.stream().filter(t -> t.startsWith("s")).forEach(t -> System.out.println(t));
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "s");
        map.put(2, "k");
        map.put(3, "r");
        map.put(4, "p");
        map.put(5, "s");
        map.put(6, "r");
        map.put(7, "u");
        map.put(8, "v");
//       map.forEach((key,value)->{
//           System.out.println(key+" : "+value);
//       });
//       map.entrySet().stream().forEach(t-> System.out.println(t));

        map.entrySet().stream().filter(k -> k.getKey() % 2 == 0).forEach(t -> System.out.println(t));
    }
}
