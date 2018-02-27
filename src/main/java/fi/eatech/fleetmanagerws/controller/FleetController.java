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

    @PostMapping("/add")
    public void createNewCar(@RequestBody Car car) {
        carService.addCar(car);
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }
}
