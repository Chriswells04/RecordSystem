package uk.ac.chester;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class MainTest {

    @Test
    void fuelConsumptionSums() {
        ArrayList<Car> carList = new ArrayList<>();
        ArrayList<Integer> fuelSums = new ArrayList<>();

        Car car1 = new Car(null, null,1,fuelConsumption.LOW);
        Car car2 = new Car(null, null,1,fuelConsumption.HIGH);
        Car car3 = new Car(null, null,1,fuelConsumption.MEDIUM);
        Car car4 = new Car(null, null,1,fuelConsumption.LOW);
        Car car5 = new Car(null, null,1,fuelConsumption.HIGH);

        carList.add(car1);
        carList.add(car2);
        carList.add(car3);
        carList.add(car4);
        carList.add(car5);

        for (int i = 0; i < 3; i++) {
            fuelSums.add(Main.fuelConsumptionSums(carList).get(i));
        }
        Assertions.assertEquals(fuelSums,Main.fuelConsumptionSums(carList));
    }

    @Test
    void meanEngineSize() {
        ArrayList<Car> carList = new ArrayList<>();

        Car car1 = new Car(null, null,2,fuelConsumption.LOW);
        Car car2 = new Car(null, null,4,fuelConsumption.HIGH);
        Car car3 = new Car(null, null,5,fuelConsumption.MEDIUM);

        carList.add(car1);
        carList.add(car2);
        carList.add(car3);

        float result3point67 = Main.meanEngineSize(carList);
        Assertions.assertEquals(3.67f,result3point67);

        ArrayList<Car> secondCarList = new ArrayList<>();

        Car car4 = new Car(null, null,2,fuelConsumption.LOW);
        Car car5 = new Car(null, null,2,fuelConsumption.HIGH);
        Car car6 = new Car(null, null,2,fuelConsumption.MEDIUM);

        secondCarList.add(car4);
        secondCarList.add(car5);
        secondCarList.add(car6);

        float result2point0 = Main.meanEngineSize(secondCarList);
        System.out.println(result2point0);
        Assertions.assertEquals(2.0f,result2point0);
        Assertions.assertFalse(result2point0 != 2.0);
    }

    @Test
    void EngineSizeMutator(){
        Car resultEngineSizeChangeUp = new Car("TestManufacture","TestName", 1, fuelConsumption.LOW);

        resultEngineSizeChangeUp.setEngineSize(2);
        Assertions.assertEquals(2, resultEngineSizeChangeUp.getEngineSize());

        Car resultEngineSizeChangeDown = new Car("TestManufacture","TestName", 1, fuelConsumption.LOW);
        resultEngineSizeChangeDown.setEngineSize(1.4f);

        Assertions.assertEquals(1.4f, resultEngineSizeChangeDown.getEngineSize());
        Assertions.assertTrue(resultEngineSizeChangeUp.getEngineSize() > resultEngineSizeChangeDown.getEngineSize(), "engine Size 1 is larger than 2");
    }

    @Test
    void motorSportTypeMutator(){
        sportsCar resultMotorSportType = new sportsCar("TestManufacture", "TestName", 1, fuelConsumption.MEDIUM, "TestSportType");
        resultMotorSportType.setMotorSportType("GT3");
        Assertions.assertEquals("GT3", resultMotorSportType.getMotorSportType());

        resultMotorSportType.setMotorSportType("Hypercar");
        Assertions.assertEquals("Hypercar", resultMotorSportType.getMotorSportType());
    }

    @Test
    void carManufactureMutator(){
        Car resultCarManufacture = new Car("TestManufacture", "TestName", 1, fuelConsumption.HIGH);
        resultCarManufacture.setCarManufacture("Ford");
        Assertions.assertEquals("Ford", resultCarManufacture.getCarManufacture());

        resultCarManufacture.setCarManufacture("ford");
        Assertions.assertEquals("ford", resultCarManufacture.getCarManufacture());

        resultCarManufacture.setCarManufacture("ForD1");
        Assertions.assertEquals("ForD1", resultCarManufacture.getCarManufacture());
    }
}