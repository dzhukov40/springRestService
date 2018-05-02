package ru.doneathome.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.doneathome.Model.Car;
import ru.doneathome.Model.mapper.CarMapper;

import java.util.List;

@Repository
public class CarDAO {

   final String ADD_CAR="INSERT INTO CAR(column1) VALUES(?)";
   final String GET_ALL_CARS="SELECT * FROM CAR";
   final String GET_CAR="SELECT * FROM CAR WHERE id = ?";
   final String DELETE_CAR="DELETE FROM CAR WHERE id = ?";
   final String DELETE_ALL_CAR="DELETE FROM CAR";

   @Qualifier("jdbcTemplateH2")
   @Autowired
   private JdbcTemplate jdbcTemplate;

   public void addCar(Car car) {
      jdbcTemplate.update(ADD_CAR, car);
   }

   public List<Car> getAllCars() {
       return jdbcTemplate.query(GET_ALL_CARS, new CarMapper());
   }

   public void updateCar(Car car) {
      jdbcTemplate.update(ADD_CAR, car);
   }

   public Car getCar(Long id) {
      return jdbcTemplate.query(GET_CAR, new Object[] { id }, new CarMapper()).get(0);
   }

   public void deleteCar(Long id) {
      jdbcTemplate.query(DELETE_CAR, new Object[] { id }, new CarMapper());
   }

   public void deleteAllCars() {
      jdbcTemplate.update(DELETE_ALL_CAR);
   }

}
