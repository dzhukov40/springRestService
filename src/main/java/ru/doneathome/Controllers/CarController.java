package ru.doneathome.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.doneathome.Model.Car;
import ru.doneathome.facade.CarFacade;
import ru.doneathome.validator.CarValidator;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarFacade carFacade;

    @Autowired
    CarValidator carValidator;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addCar(@RequestBody Car car) {
        carValidator.validate(car);
        carFacade.addCar(car);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateCar(@RequestBody Car car) {
        carFacade.updateCar(car);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Car> getAllCars() {
        return carFacade.getAllCars();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Car getCar(@PathVariable Long id) {
        return carFacade.getCar(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllCars() {
        carFacade.deleteAllCars();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCar(@PathVariable Long id) {
        carFacade.deleteCar(id);
    }

}
