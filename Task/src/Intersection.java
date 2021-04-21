import java.util.List;

public abstract class Intersection {

    public abstract Road northRoad();

    public abstract Road southRoad();

    public abstract Road eastRoad();

    public abstract Road westRoad();

    public abstract boolean canGoIntoIntersection(Road road);


    public abstract int getNumCarsPerDayNorth();

    public abstract int getNumCarsPerDaySouth();

    public abstract int getNumCarsPerDayEast();

    public abstract int getNumCarsPerDayWest();

    public abstract List<Integer> getNumListNorth();

    public abstract List<Integer> getNumListSouth();

    public abstract List<Integer> getNumListEast();

    public abstract List<Integer> getNumListWest();

    public abstract void printResults();

}
