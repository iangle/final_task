import java.util.concurrent.ThreadLocalRandom;

public class Simulation {

    //the number of hours that the simulation will run
    private final int NUMHOURS = 24;

    private final int NUMSECONDSININTERSECTION = 5;

    private static StopSign stopSign;

    private static StopSign stopSign2;

    private static TrafficLight trafficLight;


    public static void main(String[] args){
        Simulation simulation = new Simulation();

        stopSign.printResults();

        stopSign2.printResults();

        trafficLight.printResults();
    }

    public Simulation(){

        int counter  = 1;

        int numTicks = NUMHOURS * 60 * 60;

        stopSign = new StopSign("four way stop", true,true,true,true,
                3000,3000,3000,3000, numTicks);

        stopSign2 = new StopSign("two way stop", false,false,true,true,
                3000,3000,3000,3000, numTicks);

        trafficLight = new TrafficLight("traffic light",
                3000, 3000, 3000, 3000, numTicks);


        while(counter <= numTicks){


            addCarsToRoad(stopSign, numTicks, counter);

            putCarInIntersection(stopSign);

            takeCarsOutOfIntersection(stopSign, counter);


            addCarsToRoad(stopSign2, numTicks, counter);

            putCarInIntersection(stopSign2);

            takeCarsOutOfIntersection(stopSign2, counter);


            addCarsToRoad(trafficLight, numTicks, counter);

            putCarInIntersection(trafficLight);

            takeCarsOutOfIntersection(trafficLight, counter);

            if(counter % 120 == 0){
                trafficLight.northRoad().changeLightColor();

                trafficLight.southRoad().changeLightColor();

                trafficLight.eastRoad().changeLightColor();

                trafficLight.westRoad().changeLightColor();
            }



            System.out.println("current tick: " + counter);

            counter++;
        }


    }


    private void addCarsToRoad(Intersection intersection, int numTicks, int currTick){

        //get the correct numbers for each random number
        int randNumNorth = getCorrectNumber(intersection,numTicks, Location.NORTH);

        int randNumSouth = getCorrectNumber(intersection,numTicks, Location.SOUTH);

        int randNumEast = getCorrectNumber(intersection,numTicks, Location.EAST);

        int randNumWest = getCorrectNumber(intersection,numTicks, Location.WEST);


        //check to see if we should add a car to each queue or not
        if(randNumNorth < intersection.getNumCarsPerDayNorth())
            intersection.northRoad().addCarToRoad(new Car(getRandDirection(), currTick));

        if(randNumSouth < intersection.getNumCarsPerDaySouth())
            intersection.southRoad().addCarToRoad(new Car(getRandDirection(), currTick));

        if(randNumEast < intersection.getNumCarsPerDayEast())
            intersection.eastRoad().addCarToRoad(new Car(getRandDirection(), currTick));

        if(randNumWest < intersection.getNumCarsPerDayWest())
            intersection.westRoad().addCarToRoad(new Car(getRandDirection(), currTick));

    }

    //code for the random number generation from:
    // https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
    private int getRandomNumber(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }


    private Direction getRandDirection(){

        int randNum = getRandomNumber(0,2);

        switch (randNum){
            case 0:
                return Direction.LEFT;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.STRAIGHT;
        }

        return Direction.STRAIGHT;
    }

    private int getCorrectNumber(Intersection intersection, int numTicks, Location location){

        int randNum;

        while(true) {
            randNum = getRandomNumber(0, numTicks - 1);

            if(location == Location.NORTH) {
                if (intersection.getNumListNorth().contains(randNum)) {
                    intersection.getNumListNorth().remove((Integer) randNum);
                    return randNum;
                }
            }else if(location == Location.SOUTH){
                if (intersection.getNumListSouth().contains(randNum)) {
                    intersection.getNumListSouth().remove((Integer) randNum);
                    return randNum;
                }
            }else if(location == Location.EAST){
                if (intersection.getNumListEast().contains(randNum)) {
                    intersection.getNumListEast().remove((Integer) randNum);
                    return randNum;
                }
            }else if(location == Location.WEST){
                if (intersection.getNumListWest().contains(randNum)) {
                    intersection.getNumListWest().remove((Integer) randNum);
                    return randNum;
                }
            }

        }
    }


    private void putCarInIntersection(Intersection intersection) {
            if(intersection.northRoad().canGetFirstElement()) {
                if (intersection.canGoIntoIntersection(intersection.northRoad()))
                    intersection.northRoad().getCars().element().setInIntersection(true);
            }

            if(intersection.southRoad().canGetFirstElement()) {
                if (intersection.canGoIntoIntersection(intersection.southRoad()))
                    intersection.southRoad().getCars().element().setInIntersection(true);
            }

            if(intersection.eastRoad().canGetFirstElement()) {
                if (intersection.canGoIntoIntersection(intersection.eastRoad()))
                    intersection.eastRoad().getCars().element().setInIntersection(true);
            }

            if(intersection.westRoad().canGetFirstElement()) {
                if (intersection.canGoIntoIntersection(intersection.westRoad()))
                    intersection.westRoad().getCars().element().setInIntersection(true);
            }

    }


    private void takeCarsOutOfIntersection(Intersection intersection, int currTick) {

        if(intersection.northRoad().canGetFirstElement()) {
            if (!intersection.northRoad().getHasStopSign() || intersection.northRoad().getLightColor() == LightColor.GREEN || intersection.northRoad().getCars().element().getTimeSpentInIntersection() == NUMSECONDSININTERSECTION)
                intersection.northRoad().removeCarFromRoad(currTick);
        }

        if(intersection.southRoad().canGetFirstElement()) {
            if (!intersection.southRoad().getHasStopSign() || intersection.southRoad().getLightColor() == LightColor.GREEN || intersection.southRoad().getCars().element().getTimeSpentInIntersection() == NUMSECONDSININTERSECTION)
                intersection.southRoad().removeCarFromRoad(currTick);
        }

        if(intersection.eastRoad().canGetFirstElement()) {
            if (!intersection.eastRoad().getHasStopSign() || intersection.eastRoad().getLightColor() == LightColor.GREEN || intersection.eastRoad().getCars().element().getTimeSpentInIntersection() == NUMSECONDSININTERSECTION)
                intersection.eastRoad().removeCarFromRoad(currTick);
        }

        if(intersection.westRoad().canGetFirstElement()) {
            if (!intersection.westRoad().getHasStopSign() || intersection.westRoad().getLightColor() == LightColor.GREEN || intersection.westRoad().getCars().element().getTimeSpentInIntersection() == NUMSECONDSININTERSECTION)
                intersection.westRoad().removeCarFromRoad(currTick);
        }

    }

}
