package LC3901_4200;
import java.util.*;
public class LC4109_CountValidSuffixSortsII {
    /**
     * You are given two integer arrays nums and target of lengths n and m, respectively, where n <= m.
     *
     * For each index i, consider sorting the suffix nums[i..n - 1] of the original array in non-increasing order.
     *
     * Return an integer array ans of length m - n + 1, where ans[k] is the number of indices i for which the resulting
     * array equals target[k..k + n - 1].
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * A suffix of an array is a subarray that begins at any index and extends to its end.
     *
     * Input: nums = [2,2,3], target = [2,2,3,2,1,3,2,2]
     * Output: [1,1,0,0,0,1]
     *
     * Input: nums = [5,6,5], target = [5,6,5,5,6,5]
     * Output: [2,1,0,2]
     *
     * Constraints:
     *
     * n == nums.length
     * m == target.length
     * 1 <= n <= m <= 10^5
     * 1 <= nums[i], target[i] <= 10^5
     * @param nums
     * @param target
     * @return
     */
    // time = O(n + m + U), space = O(n + m + U)   U: max(max(nums), max(target))
    public int[] countSuffix(int[] nums, int[] target) {
        boolean[] validL = find(target, nums);
        int n = nums.length, m = target.length;
        int[] s = Arrays.copyOf(nums, n + m);
        System.arraycopy(target, 0, s, n, m);
        int[] z = cal(s);

        int[] lastInc = new int[m];
        for (int i = 1; i < m; i++) {
            if (target[i - 1] < target[i]) lastInc[i] = i;
            else lastInc[i] = lastInc[i - 1];
        }

        int[] res = new int[validL.length];
        for (int i = 0; i < validL.length; i++) {
            if (validL[i]) {
                res[i] = Math.max(Math.min(z[n + i], n - 1) - Math.max(lastInc[i + n - 1] - i, 0) + 1, 0);
            }
        }
        return res;
    }

    private boolean[] find(int[] s, int[] p) {
        int mx = 0;
        for (int x : s) mx = Math.max(mx, x);
        for (int x : p) mx = Math.max(mx, x);

        int[] diff = new int[mx + 1];
        for (int x : p) diff[x]++;

        boolean[] validL = new boolean[s.length - p.length + 1];
        for (int i = 0, j = 0; i < s.length; i++) {
            int x = s[i];
            diff[x]--;
            while (diff[x] < 0) diff[s[j++]]++;
            if (i - j + 1 == p.length) validL[j] = true;
        }
        return validL;
    }

    private int[] cal(int[] s) {
        int n = s.length;
        int[] z = new int[n];
        int boxL = 0, boxR = 0;
        for (int i = 1; i < n; i++) {
            if (i <= boxR) z[i] = Math.min(z[i - boxL], boxR - i + 1);
            while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
                boxL = i;
                boxR = i + z[i];
                z[i]++;
            }
        }
        return z;
    }
}