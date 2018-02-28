package fi.eatech.fleetmanagerws.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import fi.eatech.fleetmanagerws.model.Car;
import fi.eatech.fleetmanagerws.model.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Service for handling car management.
 */
@Service
public class CarService {

    private CarRepository carRepository;

    /**
     * Add a new car to the system. Does not allow more than one car with
     * identical license plate numbers.
     * @param car The car to add.
     * @return The added car.
     */
    public Car addCar(Car car) {
        if (car.getRegistrationNumber() == null
                || carRepository.findAllByRegistrationNumber(car.getRegistrationNumber()).isEmpty()) {
            carRepository.save(car);
        }
        // TODO: Exception for null or existing car.

        return car;
    }

    /**
     * Get one car with given identifier.
     * @param carId The identifier of the car to find.
     * @return The car, or null if not found.
     */
    public Car get(Long carId) {
        return carRepository.findOne(carId);
    }

    /**
     * Get all cars in the system.
     * @return List of all cars.
     */
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    /**
     * Find all cars with model year between [minYear, maxYear].
     * @param minYear Lower bound of the year range (included).
     * @param maxYear Upper bound of the year range (included).
     * @return List of all cars with correct model year.
     */
    public List<Car> getBetweenYears(int minYear, int maxYear) {
        return carRepository.findAllByModelYearBetween(minYear, maxYear);
    }

    /**
     * Find all cars made by given manufacturer.
     * @param make The manufacturer to search for.
     * @return List of all the cars in the fleet made by given manufacturer.
     */
    public List<Car> getCarsMadeBy(String make) {
        return carRepository.findAllByMake(make);
    }

    /**
     * Find all cars with given model name. Does not distinguish models
     * made by different manufacturers.
     * @param model The model name to search for.
     * @return List of all the cars of given model in the fleet.
     */
    public List<Car> getCarsOfModel(String model) {
        return carRepository.findAllByModel(model);
    }

    /**
     * Removes an existing car from the system.
     * @param carId The id of the car to remove.
     * @return The removed car, which is null if no car was found with given
     *         car identifier.
     */
    public Car remove(Long carId) {
        Car toDelete = carRepository.findOne(carId);
        if (toDelete != null) {
            carRepository.delete(carId);
        }
        return toDelete;
    }

    /**
     * Updates properties of the car with given identifier.
     * @param carId The identifier of the car to update.
     * @return The car with updated properties. If no car was found with given
     *         identifier, returns null.
     */
    public Car update(Long carId, Car updateCar) {
        Car carToUpdate = carRepository.findOne(carId);
        if (carToUpdate != null) {
            carToUpdate.update(updateCar);
            carRepository.save(carToUpdate);
        }
        return carToUpdate;
    }

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Add values from the csv file to database.
     */
    @PostConstruct
    public void init() {
        List<Car> cars = loadCarsFromCSV("fleetcars.csv");
        if (!cars.isEmpty()) {
            carRepository.save(cars);
        }
    }

    /**
     * Loads cars from CSV file (with headers) and returns them in a list.
     * @param csvFileName Name of the file to read from.
     * @return A list of all cars specified in the csv file.
     */
    public List<Car> loadCarsFromCSV(String csvFileName) {
        try {
            File csvFile = new ClassPathResource(csvFileName).getFile();
            if (csvFile.canRead()) {
                CsvMapper mapper = new CsvMapper();
                CsvSchema schema = mapper.schema().withUseHeader(true);
                MappingIterator<Car> values = mapper.readerFor(Car.class).with(schema).readValues(csvFile);
                return values.readAll();
            }
        } catch (IOException ignorableException) {}
        
        return Collections.emptyList();
    }
}
