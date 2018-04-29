package ru.doneathome.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.doneathome.Model.Car;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CarDAO {

   final String ADD_CAR="INSERT INTO CAR(column1, column2) VALUES(?,?)";
   final String GET_ALL_CARS="SELECT * FROM CAR";

   @Autowired
   JdbcTemplate jdbcTemplate;

   public void addCar(Car car) {
      jdbcTemplate.update(ADD_CAR, car);
   }

   public List<Car> getAllCars() {
      return jdbcTemplate.queryForList(GET_ALL_CARS, Car.class);
   }



}
