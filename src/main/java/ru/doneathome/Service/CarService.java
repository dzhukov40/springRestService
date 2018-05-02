package ru.doneathome.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.doneathome.DAO.CarDAO;
import ru.doneathome.Model.Car;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarDAO carDAO;

    public void addCar(Car car) {
        carDAO.addCar(car);
    }

    public void updateCar(Car car) {
        carDAO.updateCar(car);
    }

    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    public Car getCar(Long id) {
        return carDAO.getCar(id);
    }

    public void deleteCar(Long id) {
        carDAO.deleteCar(id);
    }

    public void deleteAllCars() {
        carDAO.deleteAllCars();
    }
}
