package org.example.car;


import org.example.car.Car;

import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1, "Hyndai", "1345r23"));
        cars.add(new Car(2, "BMW", "1325tf5"));
        cars.add(new Car(3, "Thar", "tsr4267"));
        cars.add(new Car(4, "swift", "vfyf2691"));
        return cars;
    }
}
