package com.crossasyst.com;

import lombok.Data;
import lombok.extern.log4j.Log4j2;



@Data @Log4j2
public class Main {
    public static void main(String[] args) {
        Person[] p1 =new Person[3];
        p1[0] =new Person();
        p1[0].setAge(12);
        p1[0].setFirstName("hitesh");
        p1[0].setLastName("ramane");

        p1[1] =new Person();
        p1[1].setAge(18);
        p1[1].setFirstName("suchit");
        p1[1].setLastName("khadtar");

        p1[2] =new Person();
        p1[2].setAge(28);
        p1[2].setFirstName("pranay");
        p1[2].setLastName("pawawr");

        for (int i = 0; i <p1.length ; i++) {
            System.out.println(p1[i].getFirstName());
        }


    }
}