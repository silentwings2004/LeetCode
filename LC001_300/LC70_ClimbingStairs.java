package LC001_300;
import java.util.*;
public class LC70_ClimbingStairs {
    /**
     * You are climbing a staircase. It takes n steps to reach the top.
     *
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     * Input: n = 2
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n <= 45
     * @param n
     * @return
     */
    // time = O(n), space = O(1)
    public int climbStairs(int n) {
        int a = 1, b = 1;
        while (--n > 0) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}