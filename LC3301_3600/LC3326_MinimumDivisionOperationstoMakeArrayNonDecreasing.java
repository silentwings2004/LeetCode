package LC3301_3600;
import java.util.*;
public class LC3326_MinimumDivisionOperationstoMakeArrayNonDecreasing {
    /**
     * You are given an integer array nums.
     *
     * Any positive divisor of a natural number x that is strictly less than x is called a proper divisor of x. For
     * example, 2 is a proper divisor of 4, while 6 is not a proper divisor of 6.
     *
     * You are allowed to perform an operation any number of times on nums, where in each operation you select any one
     * element from nums and divide it by its greatest proper divisor.
     *
     * Return the minimum number of operations required to make the array non-decreasing.
     *
     * If it is not possible to make the array non-decreasing using any number of operations, return -1.
     *
     * Input: nums = [25,7]
     * Output: 1
     *
     * Input: nums = [7,7,6]
     * Output: -1
     *
     * Input: nums = [1,1,1,1]
     * Output: 0
     *
     * Constraints:
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int minOperations(int[] nums) {
        int n = nums.length, inf = 0x3f3f3f3f;
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = get(nums[i]);
        int[][] f = new int[n][2];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], inf);
        f[0][0] = 0;
        f[0][1] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] >= nums[i - 1]) {
                f[i][0] = Math.min(f[i][0], f[i - 1][0]);
                f[i][0] = Math.min(f[i][0], f[i - 1][1]);
            } else if (p[i - 1] != 0 && nums[i] >= nums[i - 1] / p[i - 1]) {
                f[i][0] = Math.min(f[i][0], f[i - 1][1]);
            }
            if (p[i] != 0 && nums[i] / p[i] >= nums[i - 1]) {
                f[i][1] = Math.min(f[i][1], f[i - 1][0] + 1);
                f[i][1] = Math.min(f[i][1], f[i - 1][1] + 1);
            } else if (p[i] != 0 && nums[i] / p[i] >= nums[i - 1] / p[i - 1]) {
                f[i][1] = Math.min(f[i][1], f[i - 1][1] + 1);
            }
        }
        int res = Math.min(f[n - 1][0], f[n - 1][1]);
        return res == inf ? -1 : res;
    }

    private int get(int x) {
        int res = 0;
        for (int i = 1; i <= x / i; i++) {
            if (x % i == 0 && i != x) {
                res = Math.max(res, i);
                if (i != x / i && x / i != x) res = Math.max(res, x / i);
            }
        }
        return res;
    }

    // S2: LPF
    // time = O(n + UloglogU), space = O(n)
    public int minOperations2(int[] nums) {
        int n = nums.length, res = 0;
        int mx = 0;
        for (int x : nums) mx = Math.max(mx, x);
        int[] lpf = LPF(mx);
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                nums[i] = lpf[nums[i]];
                if (nums[i] > nums[i + 1]) return -1;
                res++;
            }
        }
        return res;
    }

    private int[] LPF(int mx) {
        int[] lpf = new int[mx + 1];
        for (int i = 2; i <= mx; i++) {
            if (lpf[i] == 0) {
                for (int j = i; j <= mx; j += i) {
                    if (lpf[j] == 0) lpf[j] = i;
                }
            }
        }
        return lpf;
    }
}
/**
 * x -> LPF
 * 每个数至多操作1次，变成LPF[nums[i]]
 * 每个数要么不变，要么变小
 * 最后一个数一定保持不变
 */
