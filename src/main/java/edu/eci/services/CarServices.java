/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.services;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import edu.eci.services.contracts.ICarServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andres
 */
@Component
public class CarServices implements ICarServices {

    @Autowired
    @Qualifier("CarMemoryRepository")
    //@Qualifier("CarPostgresRepository")
    private ICarRepository carRepository;

    @Override
    public List<Car> list() {
        return carRepository.findAll();
    }

    @Override
    public Car create(Car car) {
        if (null == car.getLicencePlate()) {
            throw new RuntimeException("Licence invalid");
        } else if (carRepository.find(car.getLicencePlate()) != null) {
            throw new RuntimeException("The Licence exists");
        } else {
            carRepository.save(car);
        }
        return car;
    }

    @Override
    public Car get(String name) {
        return carRepository.getCarByLicence(name);
    }

    @Override
    public void update(Car car) {
        carRepository.update(car);
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

}
