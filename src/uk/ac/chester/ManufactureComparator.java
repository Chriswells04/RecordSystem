package uk.ac.chester;

import java.util.Comparator;

public class ManufactureComparator implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return o1.getCarManufacture().compareTo(o2.getCarManufacture());
    }
}
