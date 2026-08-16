package LC3901_4200;

public class LC4025_MinimizetheMaximumWaitingTimeatSynchronizedTrafficLights {
    /**
     * You are given an integer period and an integer array lights, where lights[i] is the duration, in seconds, of the
     * green phase of the ith traffic light.
     *
     * At time 0, every traffic light starts at the beginning of its green phase. Their cycles are synchronized: every
     * traffic light starts a new cycle at the same time, and every cycle lasts exactly period seconds. Therefore, the
     * red phase of the ith traffic light lasts for period - lights[i] seconds.
     *
     * You are also given an integer array arrivalTime, where arrivalTime[j] is the arrival time, in seconds, of the
     * jth car.
     *
     * Each car must be assigned to exactly one traffic light. Multiple cars may be assigned to the same traffic light.
     * Any number of cars may cross the same traffic light simultaneously while it is green. Cars do not block or delay
     * one another.
     *
     * For a car j assigned to the ith traffic light, let r = arrivalTime[j] % period. If r < lights[i], its waiting
     * time is 0. Otherwise, its waiting time is period - r.
     *
     * The penalty of an assignment is the maximum waiting time among all cars.
     *
     * Return an integer denoting the minimum possible penalty.
     *
     * Input: period = 8, lights = [2,3], arrivalTime = [2,5,8,11]
     * Output: 5
     *
     * Input: period = 10, lights = [3,6,8], arrivalTime = [4,9,15]
     * Output: 1
     *
     * Input: period = 5, lights = [2], arrivalTime = [2,3,4,5,6]
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= period <= 10^9
     * 1 <= lights.length <= 10^4
     * 1 <= lights[i] <= period - 1
     * 1 <= arrivalTime.length <= 10^5
     * 1 <= arrivalTime[i] <= 10^9
     * @param period
     * @param lights
     * @param arrivalTime
     * @return
     */
    // time = O(n + m), space = O(1)
    public int minPenalty(int period, int[] lights, int[] arrivalTime) {
        int mx = lights[0];
        for (int x : lights) mx = Math.max(mx, x);
        int res = 0;
        for (int x : arrivalTime) {
            int r = x % period;
            int w = r < mx ? 0 : period - r;
            res = Math.max(res, w);
        }
        return res;
    }
}