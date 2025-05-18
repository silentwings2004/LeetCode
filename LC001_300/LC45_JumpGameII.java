package LC001_300;
import java.util.*;
public class LC45_JumpGameII {
    /**
     * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
     *
     * Each element in the array represents your maximum jump length at that position.
     *
     * Your goal is to reach the last index in the minimum number of jumps.
     *
     * You can assume that you can always reach the last index.
     *
     * Input: nums = [2,3,1,1,4]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // S1: DP
    // time = O(n), space = O(n)
    public int jump(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        for (int i = 1, j = 0; i < n; i++) {
            while (j + nums[j] < i) j++;
            f[i] = f[j] + 1;
        }
        return f[n - 1];
    }

    // S2: Greedy
    // time = O(n), space = O(1)
    public int jump2(int[] nums) {
        // corner case
        if (nums == null || nums.length < 2) return 0;

        int res = 0, curMax = 0, maxNext = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxNext = Math.max(maxNext, i + nums[i]);
            if (i == curMax) {
                res++;
                curMax = maxNext;
            }
        }
        return res;
    }

    // S3: Greedy
    // time = O(n), space = O(1)
    public int jump3(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        int start = 0, end = 0, step = 0;
        if (nums.length == 1) return 0;

        while (start <= end) {
            int end_new = end;
            for (int i = start; i <= end; i++) {
                end_new = Math.max(end_new, i + nums[i]);
                if (end_new >= nums.length - 1) return step + 1;
            }
            step++;
            start = end + 1;
            end = end_new;
        }
        return -1;
    }

    // S4: DP
    // time = O(n * k), space = O(n)
    public int jump4(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= nums[i]; j++) {
                if (i + j >= n) break;
                f[i + j] = Math.min(f[i + j], f[i] + 1);
            }
        }
        return f[n - 1];
    }
}
/**
 * 类似于一个贪心
 * [x] (x x) (o o) (* * * * * *)
 * 最少多少步 -> 层级遍历bfs -> 虚拟的遍历一遍就可以了，只要头和尾
 *
 * dp:
 * f[i]: 表示从i到终点的最短距离
 * 倒推一遍 => f[0] => O(n^2)
 * 优化？f[i] -> 单调的
 */
