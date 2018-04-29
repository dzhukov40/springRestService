package ru.doneathome.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doneathome.Model.Car;
import ru.doneathome.Service.CarService;

import java.util.List;

@Component
public class CarFacade {

    @Autowired
    CarService carService;

    public void addCar(Car car) {
        carService.addCar(car);
    }

    public List<Car> getAllCars() {
        return carService.getAllCars();
    }


}
