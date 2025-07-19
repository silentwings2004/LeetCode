package LC3001_3300;
import java.util.*;
public class LC3201_FindtheMaximumLengthofValidSubsequenceI {
    /**
     * You are given an integer array nums.
     * A subsequence sub of nums with length x is called valid if it satisfies:
     *
     * (sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2.
     * Return the length of the longest valid subsequence of nums.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
     * the order of the remaining elements.
     *
     * Input: nums = [1,2,3,4]
     * Output: 4
     *
     * Input: nums = [1,2,1,1,2,1,2]
     * Output: 6
     *
     * Input: nums = [1,3]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= nums.length <= 2 * 10^5
     * 1 <= nums[i] <= 10^7
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int maximumLength(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        int last0 = -1, last1 = -1;
        int v1 = 0, v2 = 0;
        // sum = 0
        for (int i = 0; i < n; i++) {
            int r = nums[i] % 2;
            if (r == 0) f[i] = last0 == -1 ? 1 : f[last0] + 1;
            else f[i] = last1 == -1 ? 1 : f[last1] + 1;
            if (r == 0) last0 = i;
            else last1 = i;
            v1 = Math.max(v1, f[i]);
        }
        last0 = -1;
        last1 = -1;
        Arrays.fill(f, 0);
        // sum = 1
        for (int i = 0; i < n; i++) {
            int r = nums[i] % 2;
            if (r == 0) f[i] = last1 == -1 ? 1 : f[last1] + 1;
            else f[i] = last0 == -1 ? 1 : f[last0] + 1;
            if (r == 0) last0 = i;
            else last1 = i;
            v2 = Math.max(v2, f[i]);
        }
        return Math.max(v1, v2);
    }

    // S2
    // time = O(n), space = O(1)
    public int maximumLength2(int[] nums) {
        return Math.max(helper(nums, 0), helper(nums, 1));
    }

    private int helper(int[] nums, int v) {
        int n = nums.length, v1 = 0, v2 = 0;
        for (int i = 0, last = -1; i < n; i++) {
            if (last == -1) {
                if (nums[i] % 2 == 0) {
                    last = nums[i] % 2;
                    v1++;
                }
            } else {
                if ((last + nums[i]) % 2 == v) {
                    last = nums[i] % 2;
                    v1++;
                }
            }
        }

        for (int i = 0, last = -1; i < n; i++) {
            if (last == -1) {
                if (nums[i] % 2 == 1) {
                    last = nums[i] % 2;
                    v2++;
                }
            } else {
                if ((last + nums[i]) % 2 == v) {
                    last = nums[i] % 2;
                    v2++;
                }
            }
        }
        return Math.max(v1, v2);
    }
}