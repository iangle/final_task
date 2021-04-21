import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Road {

    private Queue<Car> cars = new LinkedList<>();

    private Location _location;

    private Statistics stats = new Statistics();

    private LightColor _lightColor = LightColor.RED;

    private  boolean _hasStopSign;


    public Road(Location location, boolean hasStopSign){ _location = location; _hasStopSign = hasStopSign; }


    public Queue<Car> getCars(){ return cars; }

    public Location getLocation(){ return _location; }

    public void addCarToRoad(Car car){ cars.add(car); }

    public void removeCarFromRoad(int currTick){

        cars.element().setCarLeftIntersectionTick(currTick);

        stats.addWaitTime(cars.element().getTimeSpentInQueue());

        cars.remove();
    }

    public Statistics getStats(){ return stats; }


    public boolean canGetFirstElement(){

        try{
            cars.element();
            return true;

        }catch (NoSuchElementException e){
            return false;
        }

    }

    public LightColor getLightColor(){ return _lightColor; }

    public void setLightColor(LightColor color){ _lightColor = color; }

    public void changeLightColor(){

        if(_lightColor == LightColor.GREEN){
            _lightColor = LightColor.RED;

        }else{
            _lightColor = LightColor.GREEN;
        }
    }

    public boolean getHasStopSign(){ return _hasStopSign; }


}
