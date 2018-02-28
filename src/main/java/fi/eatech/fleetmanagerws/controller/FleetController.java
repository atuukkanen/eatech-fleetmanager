package fi.eatech.fleetmanagerws.controller;

import fi.eatech.fleetmanagerws.model.Car;
import fi.eatech.fleetmanagerws.service.CarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RequestMapping("/fleet")
@RestController
@EnableSwagger2
public class FleetController {

    private CarService carService;

    @ApiOperation(value = "Check that the system is up and running.")
    @GetMapping("/health")
    public ResponseEntity getHealth() {
        return ResponseEntity.ok("System up");
    }

    @ApiOperation(value = "Get all cars in the fleet.")
    @GetMapping("/all")
    public List<Car> getAllCars() {
        return carService.getAll();
    }

    @ApiOperation(value = "Get all cars in the fleet made by specific manufacturer.")
    @GetMapping("/make/{make}")
    public List<Car> getCarsByMaker(@PathVariable String make) {
        return carService.getCarsMadeBy(make);
    }

    @ApiOperation(value = "Get all cars of specific model in the fleet.")
    @GetMapping("/model/{model}")
    public List<Car> getCarsByModel(@PathVariable String model) {
        return carService.getCarsOfModel(model);
    }

    @ApiOperation(value = "Get all cars in the fleet with model year between specific years.")
    @GetMapping("/modelyear/{minYear}/{maxYear}")
    public List<Car> getCarsBetween(@PathVariable int minYear, @PathVariable int maxYear) {
        return carService.getBetweenYears(minYear, maxYear);
    }

    @ApiOperation(value = "Get the car with specific plate number.")
    @GetMapping("/{registrationNumber}")
    public Car getOne(@PathVariable String registrationNumber) {
        return carService.get(registrationNumber);
    }

    @ApiOperation(value = "Add a new car to the fleet.")
    @PostMapping("/add")
    public void createNewCar(@RequestBody Car car) {
        carService.addCar(car);
    }

    @ApiOperation(value = "Remove a car from the fleet.")
    @DeleteMapping("/{registrationNumber}")
    public Car removeOne(@PathVariable String registrationNumber) {
        return carService.remove(registrationNumber);
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }
}
