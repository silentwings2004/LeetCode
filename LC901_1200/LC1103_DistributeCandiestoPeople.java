package LC901_1200;
import java.util.*;
public class LC1103_DistributeCandiestoPeople {
    /**
     * We distribute some number of candies, to a row of n = num_people people in the following way:
     *
     * We then give 1 candy to the first person, 2 candies to the second person, and so on until we give n candies to
     * the last person.
     *
     * Then, we go back to the start of the row, giving n + 1 candies to the first person, n + 2 candies to the second
     * person, and so on until we give 2 * n candies to the last person.
     *
     * This process repeats (with us giving one more candy each time, and moving to the start of the row after we reach
     * the end) until we run out of candies.  The last person will receive all of our remaining candies (not necessarily
     * one more than the previous gift).
     *
     * Return an array (of length num_people and sum candies) that represents the final distribution of candies.
     *
     * Input: candies = 7, num_people = 4
     * Output: [1,2,3,1]
     *
     * Input: candies = 10, num_people = 3
     * Output: [5,2,3]
     *
     * Constraints:
     *
     * 1 <= candies <= 10^9
     * 1 <= num_people <= 1000
     * @param candies
     * @param num_people
     * @return
     */
    // S1: Simulation
    // time = O(m), space = O(1)
    public int[] distributeCandies(int candies, int num_people) {
        int m = candies, n = num_people;
        int[] res = new int[n];
        for (int i = 0, j = 1; m > 0; i = (i + 1) % n, j++) {
            int t = Math.min(j, m);
            res[i] += t;
            m -= t;
        }
        return res;
    }

    // S2: Math
    // time = O(n), space = O(1)
    public int[] distributeCandies2(int candies, int num_people) {
        int m = (int)((Math.sqrt(8.0 * candies + 1) - 1) / 2);
        int n = num_people;
        int k = m / n, r = m % n;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = k * (i + 1) + (k - 1) * k / 2 * n;
            if (i < r) res[i] += k * n + (i + 1);
            else if (i == r) res[i] += candies - m * (m + 1) / 2;
        }
        return res;
    }
}