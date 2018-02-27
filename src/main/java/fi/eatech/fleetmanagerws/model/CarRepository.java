package fi.eatech.fleetmanagerws.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, String> {
    List<Car> findAllByMake(String make);
    List<Car> findAllByModel(String model);
    List<Car> findAllByModelYearBetween(int min, int max);
}
