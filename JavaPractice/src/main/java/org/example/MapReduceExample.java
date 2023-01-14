package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MapReduceExample {
    public static void main(String[] args) {
        List<Integer> numbers= Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<String> words=Arrays.asList("Spring Boot","Hibernate","struct","JDBC","Java");
        int sum=0;
        for (Integer i:
             numbers) {
            sum+=i;
        }
        System.out.println(sum);

        Integer sum1=numbers.stream().reduce(0,(a,b)->a+b);
        System.out.println(sum1);

        Optional<Integer> reduceSum=numbers.stream().reduce(Integer::sum);
        System.out.println(reduceSum.get());

        Integer mulReduce=numbers.stream().reduce(1,(a,b)->a*b);
        System.out.println(mulReduce);

        Integer maxInt=numbers.stream().reduce(0,(a,b)->a>b?a:b);
        System.out.println(maxInt);

        Integer maxIntegerValue=numbers.stream().reduce(Integer::max).get();
        System.out.println(maxIntegerValue);

        String maxLengthStringValue=words.stream().reduce((word1,word2)->word1.length()>word2.length()?word1:word2).get();
        System.out.println(maxLengthStringValue);
    }
}
