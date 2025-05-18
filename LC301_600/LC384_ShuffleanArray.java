package LC301_600;
import java.util.*;
public class LC384_ShuffleanArray {
    /**
     * Given an integer array nums, design an algorithm to randomly shuffle the array. All permutations of the array
     * should be equally likely as a result of the shuffling.
     *
     * Implement the Solution class:
     *
     * Solution(int[] nums) Initializes the object with the integer array nums.
     * int[] reset() Resets the array to its original configuration and returns it.
     * int[] shuffle() Returns a random shuffling of the array.
     *
     * Input
     * ["Solution", "shuffle", "reset", "shuffle"]
     * [[[1, 2, 3]], [], [], []]
     * Output
     * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 200
     * -10^6 <= nums[i] <= 10^6
     * All the elements of nums are unique.
     * At most 5 * 10^4 calls in total will be made to reset and shuffle.
     * @param nums
     */
    int[] a;
    public LC384_ShuffleanArray(int[] nums) {
        a = nums;
    }
    // time = O(1), space = O(n)
    public int[] reset() {
        return a;
    }
    // time = O(n), space = O(n)
    public int[] shuffle() {
        int[] b = a.clone();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int j = (int)(Math.random() * n);
            int t = b[i];
            b[i] = b[j];
            b[j] = t;
        }
        return b;
    }
}