package LC1201_1500;
import java.util.*;
public class LC1365_HowManyNumbersAreSmallerThantheCurrentNumber {
    /**
     * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for
     * each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
     *
     * Return the answer in an array.
     *
     * Input: nums = [8,1,2,2,3]
     * Output: [4,0,1,1,3]
     *
     * Input: nums = [6,5,4,8]
     * Output: [2,1,0,3]
     *
     * Input: nums = [7,7,7,7]
     * Output: [0,0,0,0]
     *
     * Constraints:
     *
     * 2 <= nums.length <= 500
     * 0 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] st = new int[101];
        for (int x : nums) st[x]++;
        for (int i = 1; i <= 100; i++) st[i] += st[i - 1];
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = nums[i] == 0 ? 0 : st[nums[i] - 1];
        return res;
    }
}