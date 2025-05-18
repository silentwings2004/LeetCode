package LC2401_2700;

public class LC2651_CalculateDelayedArrivalTime {
    /**
     * You are given a positive integer arrivalTime denoting the arrival time of a train in hours, and another positive
     * integer delayedTime denoting the amount of delay in hours.
     *
     * Return the time when the train will arrive at the station.
     *
     * Note that the time in this problem is in 24-hours format.
     *
     * Input: arrivalTime = 15, delayedTime = 5
     * Output: 20
     *
     * Input: arrivalTime = 13, delayedTime = 11
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= arrivaltime < 24
     * 1 <= delayedTime <= 24
     * @param arrivalTime
     * @param delayedTime
     * @return
     */
    // time = O(1), space = O(1)
    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }
}