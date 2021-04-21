import java.util.ArrayList;
import java.util.List;

public class StopSign extends Intersection {

    private boolean _northHasStopSign, _southHasStopSign, _eastHasStopSign, _westHasStopSign;



    private final int _numCarsPerDayNorth, _numCarsPerDaySouth, _numCarsPerDayEast, _numCarsPerDayWest;

    private List<Integer> numListNorth = new ArrayList<>();

    private List<Integer> numListSouth = new ArrayList<>();

    private List<Integer> numListEast = new ArrayList<>();

    private List<Integer> numListWest = new ArrayList<>();

    private String _ID;


    private Road _northRoad;
    private Road _southRoad;
    private Road _eastRoad;
    private Road _westRoad;

    public StopSign(String ID, boolean northHasStopSign,
                    boolean southHasStopSign, boolean eastHasStopSign,
                    boolean westHasStopSign, int numCarsPerDayNorth, int numCarsPerDaySouth,
                    int numCarsPerDayEast, int numCarsPerDayWest, int numTicks)
    {

        _northHasStopSign = northHasStopSign;
        _southHasStopSign = southHasStopSign;
        _eastHasStopSign = eastHasStopSign;
        _westHasStopSign = westHasStopSign;

        _numCarsPerDayNorth = numCarsPerDayNorth;
        _numCarsPerDaySouth = numCarsPerDaySouth;
        _numCarsPerDayEast = numCarsPerDayEast;
        _numCarsPerDayWest = numCarsPerDayWest;

        _northRoad = new Road(Location.NORTH, _northHasStopSign);
        _southRoad = new Road(Location.NORTH, _southHasStopSign);
        _eastRoad = new Road(Location.NORTH, _eastHasStopSign);
        _westRoad = new Road(Location.NORTH, _westHasStopSign);

        for(int i = 0; i < numTicks; i++){
            numListNorth.add(i);
            numListSouth.add(i);
            numListEast.add(i);
            numListWest.add(i);
        }

        _ID = ID;


    }


    @Override
    public int getNumCarsPerDayNorth(){ return _numCarsPerDayNorth; }

    @Override
    public int getNumCarsPerDaySouth(){ return _numCarsPerDaySouth; }

    @Override
    public int getNumCarsPerDayEast(){ return _numCarsPerDayEast; }

    @Override
    public int getNumCarsPerDayWest(){ return _numCarsPerDayWest; }

    @Override
    public List<Integer> getNumListNorth(){ return numListNorth; }

    @Override
    public List<Integer> getNumListSouth(){ return numListSouth; }

    @Override
    public List<Integer> getNumListEast(){ return numListEast; }

    @Override
    public List<Integer> getNumListWest(){ return numListWest; }

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


    /**
     *
     * @return the northern road into the intersection
     */
    @Override
    public Road northRoad() {
        return _northRoad;
    }

    /**
     *
     * @return the southern road into the intersection
     */
    @Override
    public Road southRoad() {
        return _southRoad;
    }

    /**
     *
     * @return the eastern road into the intersection
     */
    @Override
    public Road eastRoad() { return _eastRoad; }

    /**
     *
     * @return the western road into the intersection
     */
    @Override
    public Road westRoad() { return _westRoad; }

    /**
     *
     * @param road the current road we are looking at
     * @return true if a car from the given road can enter the intersection safely
     * @throws NullPointerException if any of the roads are empty
     */
    @Override
    public boolean canGoIntoIntersection(Road road){

        //check to see if the road is the north road and has a stop sign, if so see if a car can enter the intersection
        if(_northHasStopSign && road.getLocation() == Location.NORTH) {
            if(canOppositeGoIntoIntersection(road, southRoad())){
                if(canRightGoIntoIntersection(road,westRoad())){
                    if(canLeftGoIntoIntersection(road, eastRoad())){
                        return true;
                    }
                }
            }
        }

        //check to see if the road is the south road and has a stop sign, if so see if a car can enter the intersection
        if(_southHasStopSign && road.getLocation() == Location.SOUTH){
            if(canOppositeGoIntoIntersection(road,northRoad())){
                if(canRightGoIntoIntersection(road, eastRoad())){
                    if(canLeftGoIntoIntersection(road, westRoad())){
                        return true;
                    }
                }
            }
        }

        //check to see if the road is the east road and has a stop sign, if so see if a car can enter the intersection
        if(_eastHasStopSign && road.getLocation() == Location.EAST){
            if(canOppositeGoIntoIntersection(road, westRoad())){
                if(canRightGoIntoIntersection(road, northRoad())){
                    if(canLeftGoIntoIntersection(road,southRoad())){
                        return true;
                    }
                }
            }
        }

        //check to see if the road is the west road and has a stop sign, if so see if a car can enter the intersection
        if (_westHasStopSign && road.getLocation() == Location.WEST){
            if(canOppositeGoIntoIntersection(road, eastRoad())){
                if(canRightGoIntoIntersection(road, southRoad())){
                    if(canLeftGoIntoIntersection(road, northRoad())){
                        return true;
                    }
                }
            }
        }

        //check to make sure the if the road does not have a stop sign that the car enters the intersection automatically
        if(!_northHasStopSign && road.getLocation() == Location.NORTH) {
            return true;
        }else if(!_southHasStopSign && road.getLocation() == Location.SOUTH){
            return true;
        }else if(!_eastHasStopSign && road.getLocation() == Location.EAST){
            return true;
        }else if(!_westHasStopSign && road.getLocation() == Location.WEST){
            return true;
        }


        return false;
    }


    /**
     *
     * @param road1 the current road we are looking at
     * @param road2 the road that is opposite to the road we are looking at
     * @return a boolean that is true when the car can safely enter the intersection
     * @throws NullPointerException if the queue of cars is null
     */
    private boolean canOppositeGoIntoIntersection(Road road1, Road road2){

        /*this set of if statements makes sure that if a car from the opposite road is in the intersection
          that the current and opposite cars will not collide if the current car enters the intersection */
        if (road2.canGetFirstElement() && road2.getCars().element().isInIntersection()) {

            //if the car is going left and the car opposite is going straight or right don't go into the intersection
            if (road1.canGetFirstElement() && road1.getCars().element().getDirection() == Direction.LEFT) {

                if (road2.getCars().element().getDirection() == Direction.STRAIGHT ||
                        road2.getCars().element().getDirection() == Direction.RIGHT)
                    return false;

                //if the car is going straight and the car opposite is going left don't go into the intersection
            } else if (road1.canGetFirstElement() && road1.getCars().element().getDirection() == Direction.STRAIGHT) {

                if (road2.canGetFirstElement() && road2.getCars().element().getDirection() == Direction.LEFT)
                    return false;


                //if the car is going right and the car opposite is going left don't go into the intersection
            } else if (road1.canGetFirstElement() && road1.getCars().element().getDirection() == Direction.RIGHT) {

                if (road2.canGetFirstElement() && road2.getCars().element().getDirection() == Direction.LEFT)
                    return false;

            }
        }

        return true;
    }


    /**
     *
     * @param road1 the current road we are looking at
     * @param road2 the road to the left of the road we are looking at
     * @return true if the current car can safely enter the intersection
     */
    private boolean canLeftGoIntoIntersection(Road road1, Road road2){

        if (road2.canGetFirstElement() && road2.getCars().element().isInIntersection()) {

            if (road2.getCars().element().getDirection() == Direction.STRAIGHT) {
                return false;

            } else if (road2.getCars().element().getDirection() == Direction.RIGHT) {
                return true;
            } else if (road2.getCars().element().getDirection() == Direction.LEFT) {

                if (road1.canGetFirstElement() && road1.getCars().element().getDirection() == Direction.RIGHT) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     *
     * @param road1 the current road we are looking at
     * @param road2 the road to the right of the road we are looking at
     * @return true if the current car can safely enter the intersection
     */
    private boolean canRightGoIntoIntersection(Road road1, Road road2){

        if (road2.getCars().size() == 0)
            return true;

        if (road1.canGetFirstElement() && road1.getCars().element().getDirection() == Direction.RIGHT)
            return true;

        if (road2.canGetFirstElement() && road2.getCars().element().getDirection() == Direction.RIGHT &&
                road1.getCars().element().getDirection() == Direction.STRAIGHT)
            return true;

        return false;

    }



}
