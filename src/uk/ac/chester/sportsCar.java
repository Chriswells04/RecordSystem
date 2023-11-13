package uk.ac.chester;

import java.io.Serial;
import java.io.Serializable;

public class sportsCar extends Car implements Serializable
{

    @Serial
    final private static long serialVersionUID = 1;

    public sportsCar(String carManufacture, String carName, float engineSize, uk.ac.chester.fuelConsumption fuelConsumption, String motorSportType) {
        super(carManufacture, carName, engineSize, fuelConsumption);

        this.motorSportType = motorSportType;
    }

    private String motorSportType;

    /**
     * gets motorsport type.
     * Any motorsport series e.g - WEC, NASCAR, F1
     * @return motorsport type as String
     */
    public String getMotorSportType() {
        return motorSportType;
    }


    public void setMotorSportType(String motorSportType) {
        this.motorSportType = motorSportType;
    }

    @Override
    public String toString() {
        return "Manufacture: " + getCarManufacture() + "; Car Name: " + getCarName() + "; Engine Size: " + getEngineSize() +
                "; Fuel Consumption Level: " + getFuelConsumption() + "; Motorsport Type: " + getMotorSportType();
    }
}
