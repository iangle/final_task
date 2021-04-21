import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TrafficLight extends Intersection{

    private Road _northRoad = new Road(Location.NORTH, true);
    private Road _southRoad = new Road(Location.SOUTH, true);
    private Road _eastRoad = new Road(Location.EAST, true);
    private Road _westRoad = new Road(Location.WEST, true);

    private final int _numCarsPerDayNorth, _numCarsPerDaySouth, _numCarsPerDayEast, _numCarsPerDayWest;

    private List<Integer> numListNorth = new ArrayList<>();

    private List<Integer> numListSouth = new ArrayList<>();

    private List<Integer> numListEast = new ArrayList<>();

    private List<Integer> numListWest = new ArrayList<>();

    private LightColor lightColor;

    private String _ID;

    private final int TIMETOSWITCH = 10;

    private final int GREENTIME = 120;



    public TrafficLight(String ID, int numCarsPerDayNorth, int numCarsPerDaySouth, int numCarsPerDayEast, int numCarsPerDayWest,
                        int numTicks) {

        _ID = ID;

        _numCarsPerDayNorth = numCarsPerDayNorth;

        _numCarsPerDaySouth = numCarsPerDaySouth;

        _numCarsPerDayEast = numCarsPerDayEast;

        _numCarsPerDayWest = numCarsPerDayWest;

        for(int i = 0; i < numTicks; i++){

            numListNorth.add(i);
            numListSouth.add(i);
            numListEast.add(i);
            numListWest.add(i);
        }

        northRoad().setLightColor(LightColor.GREEN);

        southRoad().setLightColor(LightColor.GREEN);

    }


    @Override
    public Road northRoad() { return _northRoad; }

    @Override
    public Road southRoad() { return _southRoad; }

    @Override
    public Road eastRoad() { return _eastRoad; }

    @Override
    public Road westRoad() { return _westRoad; }

    @Override
    public boolean canGoIntoIntersection(Road road) {

        if(road.getLightColor() == LightColor.GREEN){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int getNumCarsPerDayNorth() { return _numCarsPerDayNorth; }

    @Override
    public int getNumCarsPerDaySouth() { return _numCarsPerDaySouth; }

    @Override
    public int getNumCarsPerDayEast() { return _numCarsPerDayEast; }

    @Override
    public int getNumCarsPerDayWest() { return _numCarsPerDayWest; }


    @Override
    public List<Integer> getNumListNorth() { return numListNorth; }

    @Override
    public List<Integer> getNumListSouth() { return numListSouth; }

    @Override
    public List<Integer> getNumListEast() { return numListEast; }

    @Override
    public List<Integer> getNumListWest() { return numListWest; }


    @Override
    public void printResults() {
        System.out.println("ID: " + _ID);

        northRoad().getStats().computeAvgWaitTime();
        System.out.println("north road average wait time: " + northRoad().getStats().getAvgWaitTime()  + " seconds");

        southRoad().getStats().computeAvgWaitTime();
        System.out.println("south road average wait time: " + southRoad().getStats().getAvgWaitTime() + " seconds");

        eastRoad().getStats().computeAvgWaitTime();
        System.out.println("east road average wait time: " + eastRoad().getStats().getAvgWaitTime() + " seconds");

        westRoad().getStats().computeAvgWaitTime();
        System.out.println("west road average wait time: " + westRoad().getStats().getAvgWaitTime() + " seconds");

        System.out.println("");
    }
}
