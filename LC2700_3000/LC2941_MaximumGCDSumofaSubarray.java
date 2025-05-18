package LC2700_3000;
import java.util.*;
public class LC2941_MaximumGCDSumofaSubarray {
    /**
     * You are given an array of integers nums and an integer k.
     *
     * The gcd-sum of an array a is calculated as follows:
     *
     * Let s be the sum of all the elements of a.
     * Let g be the greatest common divisor of all the elements of a.
     * The gcd-sum of a is equal to s * g.
     * Return the maximum gcd-sum of a subarray of nums with at least k elements.
     *
     * Input: nums = [2,1,4,4,4,2], k = 2
     * Output: 48
     *
     * Input: nums = [7,3,9,4], k = 1
     * Output: 81
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 10^5
     * 1 <= nums[i] <= 10^6
     * 1 <= k <= n
     * @param nums
     * @param k
     * @return
     */
    final int M = 17;
    int[][] f;
    public long maxGcdSum(int[] nums, int k) {
        int n = nums.length;
        f = new int[n + 10][M];
        init(nums);
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        long res = 0;
        for (int i = 0; i < n; i++) {
            int l = i, t = nums[i];
            while (l < n) {
                t = gcd(t, nums[l]);
                if ((s[n] - s[i]) * t <= res) break;
                if (t == 1) {
                    res = Math.max(res, s[n] - s[i]);
                    break;
                }
                int r = find(nums, i, l, t);
                if (r - i + 1 >= k) res = Math.max(res, t * (s[r + 1] - s[i]));
                l = r + 1;
            }
        }
        return res;
    }

    private int find(int[] w, int i, int l, int t) {
        int r = w.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (query(i + 1, mid + 1) == t) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private void init(int[] w) {
        int n = w.length;
        for (int j = 0; j < M; j++) {
            for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                if (j == 0) f[i][j] = w[i - 1];
                else f[i][j] = gcd(f[i][j - 1], f[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    private int query(int l, int r) {
        int len = r - l + 1;
        int k = (int) (Math.log(len) / Math.log(2));
        return gcd(f[l][k], f[r - (1 << k) + 1][k]);
    }
}
