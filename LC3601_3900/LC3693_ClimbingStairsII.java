package LC3601_3900;
import java.util.*;
public class LC3693_ClimbingStairsII {
    /**
     * You are climbing a staircase with n + 1 steps, numbered from 0 to n.
     *
     * You are also given a 1-indexed integer array costs of length n, where costs[i] is the cost of step i.
     *
     * From step i, you can jump only to step i + 1, i + 2, or i + 3. The cost of jumping from step i to step j is
     * defined as: costs[j] + (j - i)^2
     *
     * You start from step 0 with cost = 0.
     *
     * Return the minimum total cost to reach step n.
     *
     * Input: n = 4, costs = [1,2,3,4]
     * Output: 13
     *
     * Input: n = 4, costs = [5,1,6,2]
     * Output: 11
     *
     * Input: n = 3, costs = [9,8,3]
     * Output: 12
     *
     * Constraints:
     *
     * 1 <= n == costs.length <= 10^5
     * 1 <= costs[i] <= 10^4
     * @param n
     * @param costs
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int climbStairs(int n, int[] costs) {
        int[] f = new int[n + 1];
        final int inf = 0x3f3f3f3f;
        Arrays.fill(f, inf);
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            f[i] = Math.min(f[i], f[i - 1] + + costs[i - 1] + 1);
            if (i >= 2) f[i] = Math.min(f[i], f[i - 2] + costs[i - 1] + 4);
            if (i >= 3) f[i] = Math.min(f[i], f[i - 3] + costs[i - 1] + 9);
        }
        return f[n];
    }

    // S2
    // time = O(n), space = O(1)
    public int climbStairs2(int n, int[] costs) {
        int f0 = 0, f1 = 0, f2 = 0;
        for (int x : costs) {
            int nf = Math.min(Math.min(f0 + 9, f1 + 4), f2 + 1) + x;
            f0 = f1;
            f1 = f2;
            f2 = nf;
        }
        return f2;
    }
}