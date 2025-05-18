package LC2401_2700;
import java.util.*;
public class LC2534_TimeTakentoCrosstheDoor {
    /**
     * There are n persons numbered from 0 to n - 1 and a door. Each person can enter or exit through the door once,
     * taking one second.
     *
     * You are given a non-decreasing integer array arrival of size n, where arrival[i] is the arrival time of the ith
     * person at the door. You are also given an array state of size n, where state[i] is 0 if person i wants to enter
     * through the door or 1 if they want to exit through the door.
     *
     * If two or more persons want to use the door at the same time, they follow the following rules:
     *
     * If the door was not used in the previous second, then the person who wants to exit goes first.
     * If the door was used in the previous second for entering, the person who wants to enter goes first.
     * If the door was used in the previous second for exiting, the person who wants to exit goes first.
     * If multiple persons want to go in the same direction, the person with the smallest index goes first.
     * Return an array answer of size n where answer[i] is the second at which the ith person crosses the door.
     *
     * Note that:
     *
     * Only one person can cross the door at each second.
     * A person may arrive at the door and wait without entering or exiting to follow the mentioned rules.
     *
     * Input: arrival = [0,1,1,2,4], state = [0,1,0,0,1]
     * Output: [0,3,1,2,4]
     *
     * Input: arrival = [0,0,0], state = [1,0,1]
     * Output: [0,2,1]
     *
     * Constraints:
     *
     * n == arrival.length == state.length
     * 1 <= n <= 10^5
     * 0 <= arrival[i] <= n
     * arrival is sorted in non-decreasing order.
     * state[i] is either 0 or 1.
     * @param arrival
     * @param state
     * @return
     */
    // time = O(n), space = O(n)
    public int[] timeTaken(int[] arrival, int[] state) {
        Queue<Integer> enter = new LinkedList<>();
        Queue<Integer> exit = new LinkedList<>();

        int n = arrival.length, last = -1, t = 0, tot = n, i = 0;
        int[] res = new int[n];
        while (tot > 0) {
            if (enter.isEmpty() && exit.isEmpty() && i < n) {
                if (t != arrival[i]) {
                    last = -1;
                    t = arrival[i];
                }
            }
            while (i < n && arrival[i] <= t) {
                if (state[i] == 0) enter.offer(i);
                else exit.offer(i);
                i++;
            }

            if (last == -1) {
                if (!exit.isEmpty()) {
                    res[exit.poll()] = t;
                    last = 1;
                }
                else {
                    res[enter.poll()] = t;
                    last = 0;
                }
            } else if (last == 0) {
                if (!enter.isEmpty()) {
                    res[enter.poll()] = t;
                    last = 0;
                } else {
                    res[exit.poll()] = t;
                    last = 1;
                }
            } else {
                if (!exit.isEmpty()) {
                    res[exit.poll()] = t;
                    last = 1;
                } else {
                    res[enter.poll()] = t;
                    last = 0;
                }
            }
            t++;
            tot--;
        }
        return res;
    }
}
