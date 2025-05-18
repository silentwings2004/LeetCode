package LC2700_3000;
import java.util.*;
public class LC2974_MinimumNumberGame {
    /**
     * You are given a 0-indexed integer array nums of even length and there is also an empty array arr. Alice and Bob decided to play a game where in every round Alice and Bob will do one move. The rules of the game are as follows:
     *
     * Every round, first Alice will remove the minimum element from nums, and then Bob does the same.
     * Now, first Bob will append the removed element in the array arr, and then Alice does the same.
     * The game continues until nums becomes empty.
     * Return the resulting array arr.
     *
     * Input: nums = [5,4,2,3]
     * Output: [3,2,5,4]
     *
     * Input: nums = [2,5]
     * Output: [5,2]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * nums.length % 2 == 0
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i += 2) {
            int t = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = t;
        }
        return nums;
    }
}