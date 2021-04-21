import java.util.NoSuchElementException;

/**   Isaac Angle
 *  this class will act as the cars that are a part of the simulation.
 *  these cars have a state of whether or not they are in the intersection
 *  they also have a Direction: RIGHT, LEFT or STRAIGHT.
 */
public class Car {

    private boolean inIntersection = false;

    private Direction _direction;

    private int timeSpentInIntersection = 0;

    private int carLeftIntersectionTick;

    private int _startTick;


    /**
     *
     * @param direction the direction that the car will go, right, left or straight
     */
    public Car(Direction direction, int currTick){ assert direction != null; _direction = direction; _startTick = currTick; }


    /**
     *
     * @return a boolean representing whether or not the car is in the intersection
     */
    public boolean isInIntersection(){ return inIntersection; }


    /**
     *
     * @param isTrue a boolean that tells the simulation whether or not the car is in the intersection
     */
    public void setInIntersection(boolean isTrue){ inIntersection = isTrue; }


    /**
     *
     * @return the direction that the car will be going
     */
    public Direction getDirection(){ return _direction; }


    /**
     *
     * @return the time spent in the intersection plus 1
     */
    public int getTimeSpentInIntersection(){ return timeSpentInIntersection++; }


    public void setCarLeftIntersectionTick(int tick){ carLeftIntersectionTick = tick; }


    public int getTimeSpentInQueue(){ return carLeftIntersectionTick - _startTick; }
}
