/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.services.contracts;

import edu.eci.models.Car;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Andres
 */
public interface ICarServices {
    List<Car> list();
    Car create(Car car);
    Car get(String name);
    void update(Car car) ;
    void delete(Car car); 
}
