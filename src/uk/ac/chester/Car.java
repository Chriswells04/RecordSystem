package uk.ac.chester;

import java.io.*;

public class Car implements Serializable, Comparable<Car>

{
    private String carManufacture;
    private String carName;
    private float engineSize;

    final private fuelConsumption fuelConsumption;

    // Allows data to be appended to the same save file without original data being overwritten when program restarts
    @Serial
    final private static long serialVersionUID = 1;

    // Initiates Car objects
    public Car(String carManufacture, String carName, float engineSize, fuelConsumption fuelConsumption){
        this.carManufacture = carManufacture;
        this.carName = carName;
        this.engineSize = engineSize;
        this.fuelConsumption = fuelConsumption;

    }
    public String getCarManufacture(){
        return carManufacture;
    }

    public void setCarManufacture(String carManufacture) {
        this.carManufacture = carManufacture;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    /**
     * gets engine size
     * @return engine size in litres
     */
    public float getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(float engineSize) {
        this.engineSize = engineSize;
    }

    public fuelConsumption getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public String toString(){
        return "Manufacture: " + carManufacture + "; Car Name: "+ carName + "; Engine size: " +
                engineSize +"; Fuel Consumption Level: " + fuelConsumption;
    }

    // Comparable compares fuelConsumption levels LOW,MEDIUM,HIGH in this order
    @Override
    public int compareTo(Car other) {

        return fuelConsumption.compareTo(other.fuelConsumption);
    }
}