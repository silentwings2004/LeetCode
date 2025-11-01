package LC3601_3900;
import java.util.*;
public class LC3727_MaximumAlternatingSumofSquares {
    /**
     * You are given an integer array nums. You may rearrange the elements in any order.
     *
     * The alternating score of an array arr is defined as:
     *
     * score = arr[0]2 - arr[1]2 + arr[2]2 - arr[3]2 + ...
     * Return an integer denoting the maximum possible alternating score of nums after rearranging its elements.
     *
     * Input: nums = [1,2,3]
     * Output: 12
     *
     * Input: nums = [1,-1,2,-2,3,-3]
     * Output: 16
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -4 * 10^4 <= nums[i] <= 4 * 10^4
     * @param nums
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) a[i] = nums[i];
        Arrays.sort(a, (o1, o2) -> Math.abs(o1) - Math.abs(o2));
        long res = 0;
        for (int i = 0, j = n - 1; i <= j; i++, j--) {
            if (i == j) res += 1L * a[i] * a[i];
            else res += 1L * a[j] * a[j] - 1L * a[i] * a[i];
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(logn)
    public long maxAlternatingSum2(int[] nums) {
        int n = nums.length, m = n / 2;
        for (int i = 0; i < n; i++) nums[i] *= nums[i];
        Arrays.sort(nums);
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (i < m) res -= nums[i];
            else res += nums[i];
        }
        return res;
    }
}