package uk.ac.chester;


import java.io.*;
import java.util.*;


public class Main {
    static Scanner option = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        ArrayList<Car> newCars = new ArrayList<>();
        File file = new File("save.dat");
        ObjectInputStream objectInputStream;
        System.out.println("Car Records");
        introOptions();
        // only one objectInputStream is created to prevent serialID mismatch
        if(file.isFile()){
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            newCars = (ArrayList<Car>)objectInputStream.readObject();
            objectInputStream.close();
        }
        try {
            int scan = option.nextInt();
            while (scan != 0) {
                try {
                    if (scan == 1) {
                        enterDetails(newCars);
                    }
                    if (scan == 2) {
                        displayCar(newCars);
                    }
                    if (scan == 3) {
                        deleteDetails(newCars);
                    }
                    if (scan == 4){
                        editDetails(newCars);
                    }
                    try {
                        if (scan == 5) {
                            sortDetails(newCars);
                        } if (scan == 6){
                            operationDetails(newCars);
                        }
                    }
                    catch (NullPointerException e){
                        System.out.println("Property contains null value, remove records that contain null values " + e.getMessage());
                    }
                    introReturn(newCars);
                    scan = option.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("Incorrect input" + e.getMessage());
                }
            }
            saveChanges(newCars);
        }
        catch (InputMismatchException e){
            System.out.println("incorrect value not 1-4 " + e.getMessage());
            main(null);
        }
    }

    // Options that are shown to the user upon starting the program
    public static void introOptions() {
        System.out.println("1: Enter New Car Details");
        System.out.println("2: Load Cars");
        System.out.println("3: Delete Cars");
        System.out.println("4: Edit Cars");
        System.out.println("5: sort cars");
        System.out.println("6: Operations");
        System.out.println("0: Exit");
    }

    // allows the user to enter new details for cars
    public static void enterDetails(ArrayList<Car> cars){
        boolean stop = false;
        while (!stop) {
            try {
                System.out.println("Enter Car details");
                System.out.println("Car Manufacturer: ");
                String carM = option.nextLine();
                carM = option.nextLine();
                System.out.println("Car Name: ");
                String carName = option.nextLine();
                System.out.println("Engine Size: ");
                float carEngine = option.nextFloat();
                fuelConsumption fuelC;
                if (carEngine <= 1.6){
                    fuelC = fuelConsumption.LOW;
                } else if (carEngine > 1.6 && carEngine <= 2.5) {
                    fuelC = fuelConsumption.MEDIUM;
                } else{
                    fuelC = fuelConsumption.HIGH;
                }
                System.out.println("Is this a sports car? 1/0");
                int checkForSportCar = option.nextInt();
                if (checkForSportCar == 1) {
                    // Spec series such as F1, Endurance, NASCAR
                    System.out.println("what motorsport series is this car in? ");
                    String sportsType = option.nextLine();
                    sportsType = option.nextLine();
                    sportsCar newCar = new sportsCar(carM, carName, carEngine, fuelC, sportsType);
                    cars.add(newCar);
                } else {
                    Car newCar = new Car(carM, carName, carEngine, fuelC);
                    cars.add(newCar);
                    System.out.println("Default car saved");
                }




            } catch (InputMismatchException e) {
                System.out.println("Incorrect input //error//" + e.getMessage());
            }

            System.out.println("to add more cars type 1 else type 0 to return to options");
            int additionalCars = option.nextInt();
            if (additionalCars == 0) {
                stop = true;
            }
        }
    }

    // allows records to be deleted
    public static void deleteDetails(ArrayList<Car> cars){
        boolean leave = false;
        displayCar(cars);
        while (!leave) {
            try {
                System.out.println("Enter record Number to delete: \n Or 0 to leave");
                int delChoice = option.nextInt();
                if (delChoice == 0){
                    leave = true;
                }else {
                    cars.remove(delChoice - 1);
                }
            } catch(IndexOutOfBoundsException e){
                System.out.println("Index out of bounds" + e.getMessage());
            }
        }
    }

    // allows records to be edited by each field
    public static void editDetails (ArrayList<Car> cars){
        boolean leave = false;
        displayCar(cars);
        while (!leave) {
            try {
                System.out.println("Enter record Number to update: \n Or 0 to leave");
                int upChoice = option.nextInt();
                if (upChoice == 0) {
                    leave = true;
                } else {
                    Car carToUpdate = cars.get(upChoice - 1);
                    System.out.println(carToUpdate);
                    editOptions();
                    int fieldChoice = option.nextInt();
                    if (fieldChoice == 1) {
                        System.out.println("change car manufacture: ");
                        String carManufacture = option.nextLine();
                        carManufacture = option.nextLine();
                        carToUpdate.setCarManufacture(carManufacture);
                    } else if (fieldChoice == 2) {
                        System.out.println("change car Name: ");
                        String carName = option.nextLine();
                        carName = option.nextLine();
                        carToUpdate.setCarName(carName);
                    } else if (fieldChoice == 3) {
                        System.out.println("change engine size: ");
                        float engineSize = option.nextFloat();
                        engineSize = option.nextFloat();
                        carToUpdate.setEngineSize(engineSize);
                    }
                }
            }
            catch (InputMismatchException e){
                System.out.println("1-5 your value did not match the required range" + e.getMessage());
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("Index Out of Range" + e.getMessage());
            }
        }
    }

    // when the sort option is selected from the main interface this will display
    public static void sortOptions (){
        System.out.println("Choose which field to sort by (1-4)");
        System.out.println("1: Car Manufacture");
        System.out.println("2: Car Name");
        System.out.println("3: Fuel Consumption Level");
        System.out.println("4: Engine Size");
        System.out.println("0: exit");
    }

    // Allows records to be sorted by most fields
    public static void sortDetails(ArrayList<Car> cars){
        sortOptions();
        int sortChoice = option.nextInt();
        switch (sortChoice) {
            // Comparator for case 1,2,4
            case 1 -> {
                ManufactureComparator manufactureComparator = new ManufactureComparator();
                Collections.sort(cars, manufactureComparator);
                displayCar(cars);
            } case 2 -> {
                NameComparator nameComparator = new NameComparator();
                Collections.sort(cars, nameComparator);
                displayCar(cars);
            }
            // comparable used for case 3
            case 3 -> {
                Collections.sort(cars);
                displayCar(cars);
            } case 4 -> {
                EngineSizeComparator engineSizeComparator = new EngineSizeComparator();
                Collections.sort(cars, engineSizeComparator);
                displayCar(cars);
            }default -> System.out.println("Input out of bounds");
        }
    }
    // When the operation option is selected this menu will be shown
    public static void operationOptions (){
        System.out.println("Perform Operations on Saved Data (1-2):\nAvailable Operations:");
        System.out.println("1. Sum of fuel consumption Levels");
        System.out.println("2. Mean of all car engine sizes");
    }

    // allows different operations to be sorted on records that are saved
    public static void operationDetails(ArrayList<Car> cars){
        operationOptions();
        int operationChoice = option.nextInt();
        switch (operationChoice){
            // totals each car record by its fuel consumption level
            case 1 -> {
                System.out.println("Total of each fuel consumption level -");
                System.out.println("Low: " + fuelConsumptionSums(cars).get(0));
                System.out.println("Medium: " + fuelConsumptionSums(cars).get(1));
                System.out.println("High: " + fuelConsumptionSums(cars).get(2));
            }
            // finds the mean engine size of all cars saved
            case 2-> {
                System.out.println("Mean engine size of all cars saved - ");
                System.out.println(meanEngineSize(cars) + " litres");
            }
        }
    }
    // when edit option is selected this menu will be shown
    public static void editOptions(){
        System.out.println("enter number to edit a single field: ");
        System.out.println("1: Car Manufacture");
        System.out.println("2: Car Name");
        System.out.println("3: Engine Size");
        System.out.println("0: exit");
    }

    // changes made can be saved or not before exiting
    public static void saveChanges(ArrayList<Car> cars){
        System.out.println("Save changes before closing the program? yes(1)/no(0)");
        Scanner option = new Scanner(System.in);
            int saveChange = option.nextInt();
            if (saveChange == 1) {
                saveCars(cars);
                System.out.println("Changes saved, exit");
            }
            else {
                System.out.println("exit");
            }
    }

    // gives the option to return to the main menu or exit program
    public static void introReturn(ArrayList<Car> cars){
        Scanner option = new Scanner(System.in);
        System.out.println("return to options or exit 0/1?");
        int introReturn = option.nextInt();
            if (introReturn == 0) {
                introOptions();
            } else if (introReturn == 1){
                saveChanges(cars);
                System.exit(0);
            } else{
                System.out.println("Input out of bounds");
                introOptions();
            }
    }
    // displays all car records that are available
    public static void displayCar(ArrayList<Car> cars){
        int recordNo = 0;
        for (Car car : cars) {
            recordNo = recordNo + 1;
            System.out.println(recordNo + ": " + car);
        }
    }

    // saves changes to the save file
    public static void saveCars(ArrayList<Car> cars) {
        try {
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("save.dat"));
            objectOutputStream.writeObject(cars);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("unable to save file");
        } catch (IOException e) {
            System.out.println("unable to create object stream" + e.getMessage());
        }
    }

    // calculates the total of each fuel level of all car records available
     public static ArrayList<Integer> fuelConsumptionSums (ArrayList<Car> cars){
        ArrayList<Integer> fuelSums = new ArrayList<>();
         int low = 0;
         int med = 0;
         int high = 0;
         for (Car car : cars) {
             if (car.getFuelConsumption() == fuelConsumption.LOW) {
                 low++;
             } else if (car.getFuelConsumption() == fuelConsumption.MEDIUM) {
                 med++;
             } else if (car.getFuelConsumption() == fuelConsumption.HIGH) {
                 high++;
             }
         }
         fuelSums.add(low);
         fuelSums.add(med);
         fuelSums.add(high);
         return fuelSums;
     }

     // calculates the mean engine size of all car records that are available
     public static float meanEngineSize (ArrayList<Car> cars){
        float meanEngineSize = 0;
         for (Car car : cars) {
             meanEngineSize += car.getEngineSize();
         }

         return Math.round(meanEngineSize / cars.size() * 100.0) / 100.0f;
     }
}
