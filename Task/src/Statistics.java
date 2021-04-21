/* Isaac Angle
 * this class is used to collect the statistics of the simulation
 * namely we are looking for the average wait time of cars and the amount
 * of cars that were left in the queue after the simulation has run
 */

import java.util.ArrayList;
import java.util.List;

public class Statistics {

    /*the average time that a car had to wait at the intersection*/
    private double avgWaitTime;

    /*a list of wait times that all the cars in the queue experienced*/
    private List<Integer> waitTimes = new ArrayList<>();

    /*the number of cars left in the queue after the simulation has run*/
    private int numCarsLeft;


    public Statistics(){ }

    /**
     *
     * @param numCars the number of cars that are left in the queue
     */
    public void updateNumCarLeft(int numCars){ numCarsLeft = numCars; }

    /**
     *
     * @return the number of cars left in the queue
     */
    public int getNumCarsLeft(){ return numCarsLeft; }

    /**
     *
     * @param time the time that we are adding to the waitTimes list
     */
    public void addWaitTime(int time) { waitTimes.add(time); }

    /**
     *
     * @return the average wait time of a car in the queue
     */
    public double getAvgWaitTime() { return avgWaitTime; }

    /**
     * compute the average wait time of the cars in the queue
     */
    public void computeAvgWaitTime(){

        assert waitTimes.size() != 0;

        double sum = 0;

        for(int i = 0; i < waitTimes.size(); i++)
            sum += waitTimes.get(i);


        avgWaitTime = sum / waitTimes.size();
    }



}
