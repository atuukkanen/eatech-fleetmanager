package fi.eatech.fleetmanagerws.controller;

import fi.eatech.fleetmanagerws.model.Car;
import fi.eatech.fleetmanagerws.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/fleet")
@RestController
public class FleetController {

    private CarService carService;

    @GetMapping("/health")
    public ResponseEntity getHealth() {
        return ResponseEntity.ok("System up");
    }

    @GetMapping("/all")
    public List<Car> getAllCars() {
        return carService.getAll();
    }

    @GetMapping("/make/{make}")
    public List<Car> getCarsByMaker(@PathVariable String make) {
        return carService.getCarsMadeBy(make);
    }

    @GetMapping("/model/{model}")
    public List<Car> getCarsByModel(@PathVariable String model) {
        return carService.getCarsOfModel(model);
    }

    @GetMapping("/modelyear/{minYear}/{maxYear}")
    public List<Car> getCarsBetween(@PathVariable int minYear, @PathVariable int maxYear) {
        return carService.getBetweenYears(minYear, maxYear);
    }

    @GetMapping("/{registrationNumber}")
    public Car getOne(@PathVariable String registrationNumber) {
        return carService.get(registrationNumber);
    }

    @PostMapping("/add")
    public void createNewCar(@RequestBody Car car) {
        carService.addCar(car);
    }

    @DeleteMapping("/{registrationNumber}")
    public Car removeOne(@PathVariable String registrationNumber) {
        return carService.remove(registrationNumber);
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }
}
