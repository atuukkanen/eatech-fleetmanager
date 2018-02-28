package fi.eatech.fleetmanagerws.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Represents a car of the fleet with unique license plate number.
 */
@Entity
@ApiModel(description = "Represents a car of the fleet.")
public class Car {
    @Id
    @NotNull
    @ApiModelProperty(required = true)
    private String registrationNumber;

    private String make;
    private String model;
    private int modelYear;
    private double engineSize;
    private int enginePower;
    private LocalDate lastInspection;

    public Car() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public LocalDate getLastInspection() {
        return lastInspection;
    }

    public void setLastInspection(LocalDate lastInspection) {
        this.lastInspection = lastInspection;
    }
}
