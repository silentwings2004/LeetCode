package LC2401_2700;
import java.util.*;
public class LC2442_CountNumberofDistinctIntegersAfterReverseOperations {
    /**
     * You are given an array nums consisting of positive integers.
     *
     * You have to take each integer in the array, reverse its digits, and add it to the end of the array. You should
     * apply this operation to the original integers in nums.
     *
     * Return the number of distinct integers in the final array.
     *
     * Input: nums = [1,13,10,12,31]
     * Output: 6
     *
     * Input: nums = [2,2,2]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int countDistinctIntegers(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
            set.add(reverse(x));
        }
        return set.size();
    }

    private int reverse(int x) {
        int res = 0;
        while (x > 0) {
            int t = x % 10;
            res = res * 10 + t;
            x /= 10;
        }
        return res;
    }
}
