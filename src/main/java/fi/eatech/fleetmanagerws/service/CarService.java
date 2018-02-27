package fi.eatech.fleetmanagerws.service;

import fi.eatech.fleetmanagerws.model.Car;
import fi.eatech.fleetmanagerws.model.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for handling car management.
 */
@Service
public class CarService {

    private CarRepository carRepository;

    public Car addCar(Car car) {
        carRepository.save(car);
        return car;
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
}
