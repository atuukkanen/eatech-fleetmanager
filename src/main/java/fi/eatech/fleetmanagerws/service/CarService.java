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
        if (car.getRegistrationNumber() != null
                && carRepository.findOne(car.getRegistrationNumber()) == null) {
            carRepository.save(car);
        }
        // TODO: Exception for null or existing car.

        return car;
    }

    public Car get(String registrationNumber) {
        return carRepository.findOne(registrationNumber);
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public List<Car> getBetweenYears(int minYear, int maxYear) {
        return carRepository.findAllByModelYearBetween(minYear, maxYear);
    }

    public List<Car> getCarsMadeBy(String make) {
        return carRepository.findAllByMake(make);
    }

    public List<Car> getCarsOfModel(String model) {
        return carRepository.findAllByModel(model);
    }

    public Car remove(String registrationNumber) {
        Car toDelete = carRepository.findOne(registrationNumber);
        if (toDelete != null) {
            carRepository.delete(registrationNumber);
        }
        return toDelete;
    }

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
}
