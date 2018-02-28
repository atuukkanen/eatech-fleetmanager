package fi.eatech.fleetmanagerws.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fi.eatech.fleetmanagerws.tool.LocalDateDeserializer;
import fi.eatech.fleetmanagerws.tool.LocalDateSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Represents a car of the fleet with unique license plate number.
 */
@Entity
@ApiModel(description = "Represents a car of the fleet.")
public class Car extends AbstractPersistable<Long> {
    private String make;
    private String model;
    private String registrationNumber;
    private Integer modelYear;
    private Integer enginePower;
    private Double engineSize;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public Double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(Double engineSize) {
        this.engineSize = engineSize;
    }

    public LocalDate getLastInspection() {
        return lastInspection;
    }

    public void setLastInspection(LocalDate lastInspection) {
        this.lastInspection = lastInspection;
    }

    /**
     * Update car's properties with values from given car object. All null
     * values get ignored.
     * @param updateCar The object containing new values for properties.
     */
    public void update(Car updateCar) {
        if (updateCar.getMake() != null) {
            setMake(updateCar.getMake());
        }
        if (updateCar.getModel() != null) {
            setModel(updateCar.getModel());
        }
        if (updateCar.getModelYear() != null) {
            setModelYear(updateCar.getModelYear());
        }
        if (updateCar.getRegistrationNumber() != null) {
            setRegistrationNumber(updateCar.getRegistrationNumber());
        }
        if (updateCar.getEngineSize() != null) {
            setEngineSize(updateCar.getEngineSize());
        }
        if (updateCar.getEnginePower() != null) {
            setEnginePower(updateCar.getEnginePower());
        }
        if (updateCar.getLastInspection() != null) {
            setLastInspection(updateCar.getLastInspection());
        }
    }
}
