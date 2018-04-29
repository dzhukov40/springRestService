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

    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }
}
