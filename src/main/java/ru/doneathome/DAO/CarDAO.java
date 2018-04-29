package ru.doneathome.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.doneathome.Model.Car;
import ru.doneathome.Model.mapper.CarMapper;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CarDAO {

   final String ADD_CAR="INSERT INTO CAR(column1, column2) VALUES(?,?)";
   final String DELETE_CAR="DELETE from customer where custid=?";
   final String GET_ALL_CARS="SELECT * FROM CAR";


   @Qualifier("jdbcTemplateH2")
   @Autowired
   private JdbcTemplate jdbcTemplate;

   public void addCar(Car car) {
      jdbcTemplate.update(ADD_CAR, car);
   }

   public void deleteCar(int id) {
       jdbcTemplate.update(DELETE_CAR, id);
   }

   public List<Car> getAllCars() {
       return jdbcTemplate.query(GET_ALL_CARS, new CarMapper());
   }


}
