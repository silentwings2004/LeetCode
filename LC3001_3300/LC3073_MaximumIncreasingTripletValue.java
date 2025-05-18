package LC3001_3300;
import java.util.*;
public class LC3073_MaximumIncreasingTripletValue {
    /**
     * Given an array nums, return the maximum value of a triplet (i, j, k) such that i < j < k and
     * nums[i] < nums[j] < nums[k].
     *
     * The value of a triplet (i, j, k) is nums[i] - nums[j] + nums[k].
     * Input: nums = [5,6,9]
     *
     * Output: 8
     *
     * Input:  nums = [1,5,3,6]
     * Output:  4
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * The input is generated such that at least one triplet meets the given condition.
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maximumTripletValue(int[] nums) {
        int n = nums.length;
        int[] r = new int[n];
        r[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) r[i] = Math.max(nums[i], r[i + 1]);
        int res = 0;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(nums[0]);
        for (int i = 1; i < n - 1; i++) {
            Integer pre = set.lower(nums[i]);
            int suf = r[i + 1];
            if (pre != null && suf > nums[i]) res = Math.max(res, pre + suf - nums[i]);
            set.add(nums[i]);
        }
        return res;
    }
}