package ru.doneathome.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.doneathome.Model.Car;
import ru.doneathome.facade.CarFacade;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarFacade carFacade;


    @RequestMapping(method = RequestMethod.POST)
    public void addCar(@RequestBody Car car) {
        carFacade.addCar(car);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Car> getAllCars() {
        return carFacade.getAllCars();
    }


}
