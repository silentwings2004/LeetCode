package LC3901_4200;
import java.util.*;
public class LC4093_MaximizeSumAfterAtMostKDigitRearrangements {
    /**
     * You are given an integer array nums of length n and an integer k.
     *
     * In one operation, you may choose any element of nums and rearrange its decimal digits in any order to form a new
     * integer.
     *
     * Return the maximum possible sum of the array after performing at most k operations.
     *
     * Input: nums = [123,45], k = 1
     * Output: 366
     *
     * Input: nums = [102,90,14], k = 2
     * Output: 341
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= n
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public long maximizeSum(int[] nums, int k) {
        int n = nums.length;
        long[][] w = new long[n][2];
        for (int i = 0; i < n; i++) {
            long v = get(nums[i]);
            w[i] = new long[]{v, i};
        }
        Arrays.sort(w, (o1, o2) -> Long.compare(o2[0] - nums[(int)o2[1]], o1[0] - nums[(int)o1[1]]));
        long res = 0;
        for (int i = 0; i < n; i++) {
            int idx = (int)w[i][1];
            if (w[i][0] == nums[idx]) res += nums[idx];
            else {
                if (k > 0 && w[i][0] > nums[idx]) {
                    res += w[i][0];
                    k--;
                } else res += nums[idx];
            }
        }
        return res;
    }

    private long get(int x) {
        List<Integer> q = new ArrayList<>();
        while (x > 0) {
            int v = x % 10;
            x /= 10;
            q.add(v);
        }
        Collections.sort(q, (o1, o2) -> o2 - o1);
        long res = 0;
        for (int y : q) res = res * 10 + y;
        return res;
    }

    // S2
    // time = O(n * m * logm + nlogn), space = O(m + logn)
    public long maximizeSum2(int[] nums, int k) {
        int n = nums.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            res += x;
            char[] s = String.valueOf(x).toCharArray();
            Arrays.sort(s);
            int y = 0;
            for (int j = s.length - 1; j >= 0; j--) y = y * 10 + s[j] - '0';
            nums[i] = y - x;
        }

        Arrays.sort(nums);
        for (int i = n - 1; i >= n - k; i--) res += nums[i];
        return res;
    }
}