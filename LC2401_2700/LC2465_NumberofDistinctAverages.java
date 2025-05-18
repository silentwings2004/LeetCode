package LC2401_2700;
import java.util.*;
public class LC2465_NumberofDistinctAverages {
    /**
     * You are given a 0-indexed integer array nums of even length.
     *
     * As long as nums is not empty, you must repetitively:
     *
     * Find the minimum number in nums and remove it.
     * Find the maximum number in nums and remove it.
     * Calculate the average of the two removed numbers.
     * The average of two numbers a and b is (a + b) / 2.
     *
     * For example, the average of 2 and 3 is (2 + 3) / 2 = 2.5.
     * Return the number of distinct averages calculated using the above process.
     *
     * Note that when there is a tie for a minimum or maximum number, any can be removed.
     *
     * Input: nums = [4,1,4,0,3,5]
     * Output: 2
     *
     * Input: nums = [1,100]
     * Output: 1
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * nums.length is even.
     * 0 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int distinctAverages(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        while (i < j) set.add(nums[i++] + nums[j--]);
        return set.size();
    }
}
