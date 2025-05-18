package LC2401_2700;
import java.util.*;
public class LC2670_FindtheDistinctDifferenceArray {
    /**
     * You are given a 0-indexed array nums of length n.
     *
     * The distinct difference array of nums is an array diff of length n such that diff[i] is equal to the number of
     * distinct elements in the suffix nums[i + 1, ..., n - 1] subtracted from the number of distinct elements in the
     * prefix nums[0, ..., i].
     *
     * Return the distinct difference array of nums.
     *
     * Note that nums[i, ..., j] denotes the subarray of nums starting at index i and ending at index j inclusive.
     * Particularly, if i > j then nums[i, ..., j] denotes an empty subarray.
     *
     * Input: nums = [1,2,3,4,5]
     * Output: [-3,-1,1,3,5]
     *
     * Input: nums = [3,2,3,4,2]
     * Output: [-2,-1,0,2,3]
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 50
     * 1 <= nums[i] <= 50
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int[] distinctDifferenceArray(int[] nums) {
        int n = nums.length;
        int[] a = new int[n], b = new int[n + 1];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
            a[i] = set.size();
        }
        set.clear();
        for (int i = n - 1; i >= 0; i--) {
            set.add(nums[i]);
            b[i] = set.size();
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = a[i] - b[i + 1];
        return res;
    }
}