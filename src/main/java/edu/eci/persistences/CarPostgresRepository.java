/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.persistences;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Andres
 */
public class CarPostgresRepository implements ICarRepository{
    private String dbUrl = "postgres://nylcasefkljgdu:c432d8924c680a77808241d131768bb3220cabf5ea1142853a08d9d13f978db2@ec2-23-23-195-205.compute-1.amazonaws.com:5432/de17vsdid3stge";
    @Autowired
    private DataSource dataSource;
    @Override
    public Car getCarByLicence(String licence) {
        String query = "SELECT * FORM cars WHERE licencePlate = " + licence + ";";
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Car car = new Car();
            car.setLicencePlate(licence);
            car.setBrand(rs.getString("brand"));
            return car;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM cars;";
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Car car = new Car();
                car.setBrand(rs.getString("brand"));
                car.setLicencePlate(rs.getString("licencePlate"));
                cars.add(car);
            }
            return cars;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car find(String id) {
        String query = "SELECT * FORM cars WHERE id = " + id.toString() + ";";
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Car car = new Car();
            car.setBrand(rs.getString("brand"));
            car.setLicencePlate(rs.getString("licencePlate"));
            return car;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public String save(Car entity) {
        String query = "INSERT INTO cars(licencePlate, brand) VALUES (" + entity.getLicencePlate()+ "," + entity.getBrand() + ")";
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            return entity.getLicencePlate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Car entity) {
        String query = "UPDATE cars SET brand = "+ entity.getBrand() + " WHERE licencePlate = " + entity.getLicencePlate().toString() + "";
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Car o) {
        String query = "DELETE FROM users WHERE licencePlate = " + o.getLicencePlate()+ "";
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        }
    }
    
}
