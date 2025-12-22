package LC3601_3900;
import java.util.*;
public class LC3780_MaximumSumofThreeNumbersDivisiblebyThree {
    /**
     * You are given an integer array nums.
     *
     * Your task is to choose exactly three integers from nums such that their sum is divisible by three.
     *
     * Return the maximum possible sum of such a triplet. If no such triplet exists, return 0.
     *
     * Input: nums = [4,2,3,1]
     * Output: 9
     *
     * Input: nums = [2,1,5]
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maximumSum(int[] nums) {
        List<Integer>[] q = new List[3];
        for (int i = 0; i < 3; i++) q[i] = new ArrayList<>();
        for (int x : nums) q[x % 3].add(x);
        for (int i = 0; i < 3; i++) Collections.sort(q[i], (o1, o2) -> o2 - o1);
        int res = 0;
        for (int i = 0; i < 3; i++) {
            if (q[i].size() >= 3) {
                res = Math.max(res, q[i].get(0) + q[i].get(1) + q[i].get(2));
            }
        }
        if (q[0].size() > 0 && q[1].size() > 0 && q[2].size() > 0) {
            res = Math.max(res, q[0].get(0) + q[1].get(0) + q[2].get(0));
        }
        return res;
    }

    // S2: dp
    // time = O(n * k * m), space = O(k * m)
    public int maximumSum2(int[] nums) {
        int k = 3, mod = 3;
        int[][] f = new int[k + 1][mod];
        for (int[] x : f) Arrays.fill(x, Integer.MIN_VALUE);
        f[0][0] = 0;
        for (int x : nums) {
            for (int j = k; j > 0; j--) {
                for (int r = 0; r < mod; r++) {
                    f[j][r] = Math.max(f[j][r], f[j - 1][(r - x % mod + mod) % mod] + x);
                }
            }
        }
        return Math.max(f[k][0], 0);
    }
}
/**
 * 二维0-1背包问题(LC474)
 */
