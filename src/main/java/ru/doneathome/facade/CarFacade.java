package ru.doneathome.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doneathome.Model.Car;
import ru.doneathome.Service.CarService;
import ru.doneathome.validator.CarValidator;

import java.util.List;

@Component
public class CarFacade {

    @Autowired
    CarService carService;

    public void addCar(Car car) {
        carService.addCar(car);
    }

    public void updateCar(Car car) { carService.updateCar(car); }

    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    public Car getCar(Long id) { return carService.getCar(id); }

    public void deleteCar(Long id) { carService.deleteCar(id); }

    public void deleteAllCars() { carService.deleteAllCars(); }
}
