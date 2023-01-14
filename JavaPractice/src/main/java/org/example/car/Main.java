package org.example.car;

import java.util.Collections;
import java.util.List;

public class Main {
    public List<Car> getCarsInSorted() {
        List<Car> carList = new CarDAO().getCars();
        Collections.sort(carList, (t1, t2) ->
                t1.getName().compareTo(t2.getName())
        );
        return carList;
    }

    public static void main(String[] args) {


        System.out.println(new Main().getCarsInSorted());
    }
}