package LC3601_3900;

public class LC3894_TrafficSignalColor {
    /**
     * You are given an integer timer representing the remaining time (in seconds) on a traffic signal.
     *
     * The signal follows these rules:
     *
     * If timer == 0, the signal is "Green"
     * If timer == 30, the signal is "Orange"
     * If 30 < timer <= 90, the signal is "Red"
     * Return the current state of the signal. If none of the above conditions are met, return "Invalid".
     *
     * Input: timer = 60
     * Output: "Red"
     *
     * Input: timer = 5
     * Output: "Invalid"
     *
     * Constraints:
     *
     * 0 <= timer <= 1000
     * @param timer
     * @return
     */
    // time = O(1), space = O(1)
    public String trafficSignal(int timer) {
        if (timer == 0) return "Green";
        if (timer == 30) return "Orange";
        if (timer > 30 && timer <= 90) return "Red";
        return "Invalid";
    }
}