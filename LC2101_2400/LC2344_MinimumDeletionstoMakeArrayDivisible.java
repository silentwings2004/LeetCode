package LC2101_2400;
import java.util.*;
public class LC2344_MinimumDeletionstoMakeArrayDivisible {
    /**
     * You are given two positive integer arrays nums and numsDivide. You can delete any number of elements from nums.
     *
     * Return the minimum number of deletions such that the smallest element in nums divides all the elements of
     * numsDivide. If this is not possible, return -1.
     *
     * Note that an integer x divides y if y % x == 0.
     *
     * Input: nums = [2,3,2,4,3], numsDivide = [9,6,9,3,15]
     * Output: 2
     *
     * Input: nums = [4,3,6], numsDivide = [8,2,6,10]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length, numsDivide.length <= 10^5
     * 1 <= nums[i], numsDivide[i] <= 10^9
     * @param nums
     * @param numsDivide
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int minOperations(int[] nums, int[] numsDivide) {
        int d = 0;
        for (int x : numsDivide) d = gcd(d, x);

        int res = 0, n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (d % nums[i] == 0) break;
            res++;
        }
        return res == n ? -1 : res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
