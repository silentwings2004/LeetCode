package LC2700_3000;

public class LC2739_TotalDistanceTraveled {
    /**
     * A truck has two fuel tanks. You are given two integers, mainTank representing the fuel present in the main tank
     * in liters and additionalTank representing the fuel present in the additional tank in liters.
     *
     * The truck has a mileage of 10 km per liter. Whenever 5 liters of fuel get used up in the main tank, if the
     * additional tank has at least 1 liters of fuel, 1 liters of fuel will be transferred from the additional tank to
     * the main tank.
     *
     * Return the maximum distance which can be traveled.
     *
     * Note: Injection from the additional tank is not continuous. It happens suddenly and immediately for every 5
     * liters consumed.
     *
     * Input: mainTank = 5, additionalTank = 10
     * Output: 60
     *
     * Input: mainTank = 1, additionalTank = 2
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= mainTank, additionalTank <= 100
     * @param mainTank
     * @param additionalTank
     * @return
     */
    // S1: Simulation
    // time = O(1), space = O(1)
    public int distanceTraveled(int mainTank, int additionalTank) {
        int res = 0;
        while (mainTank > 0) {
            if (mainTank >= 5) {
                res += 50;
                mainTank -= 5;
                if (additionalTank > 0) {
                    additionalTank--;
                    mainTank++;
                }
            } else {
                res += mainTank * 10;
                break;
            }
        }
        return res;
    }

    // S2: Math
    // time = O(1), space = O(1)
    public int distanceTraveled2(int mainTank, int additionalTank) {
        return (mainTank + Math.min(additionalTank, (mainTank - 1) / 4)) * 10;
    }
}