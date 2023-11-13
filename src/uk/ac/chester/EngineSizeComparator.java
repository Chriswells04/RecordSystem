package uk.ac.chester;

import java.util.Comparator;

public class EngineSizeComparator implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return (int) (o1.getEngineSize() - o2.getEngineSize());
    }
}
