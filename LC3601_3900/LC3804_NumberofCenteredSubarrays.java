package LC3601_3900;
import java.util.*;
public class LC3804_NumberofCenteredSubarrays {
    /**
     * You are given an integer array nums.
     *
     * A subarray of nums is called centered if the sum of its elements is equal to at least one element within that
     * same subarray.
     *
     * Return the number of centered subarrays of nums.
     *
     * Input: nums = [-1,1,0]
     * Output: 5
     *
     * Input: nums = [2,-3]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 500
     * -10^5 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(n)
    public int centeredSubarrays(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            HashSet<Integer> set = new HashSet<>();
            int s = 0;
            for (int j = i; j < n; j++) {
                s += nums[j];
                set.add(nums[j]);
                if (set.contains(s)) res++;
            }
        }
        return res;
    }
}