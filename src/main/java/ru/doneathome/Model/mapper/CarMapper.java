package ru.doneathome.Model.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.doneathome.Model.Car;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {

    public Car mapRow(ResultSet rs, int rows) throws SQLException {
        Car car = new Car();

        car.setId(rs.getLong("id"));
        car.setModel(rs.getString("model"));
        car.setVendor(rs.getString("vendor"));

        return car;
    }

}