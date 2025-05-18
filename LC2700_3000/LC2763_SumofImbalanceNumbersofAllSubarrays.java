package LC2700_3000;
import java.util.*;
public class LC2763_SumofImbalanceNumbersofAllSubarrays {
    /**
     * The imbalance number of a 0-indexed integer array arr of length n is defined as the number of indices in
     * sarr = sorted(arr) such that:
     *
     * 0 <= i < n - 1, and
     * sarr[i+1] - sarr[i] > 1
     * Here, sorted(arr) is the function that returns the sorted version of arr.
     *
     * Given a 0-indexed integer array nums, return the sum of imbalance numbers of all its subarrays.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [2,3,1,4]
     * Output: 3
     *
     * Input: nums = [1,3,3,3,5]
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= nums.length
     * @param nums
     * @return
     */
    // time = O(n^2*logn), space = O(n)
    public int sumImbalanceNumbers(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            TreeSet<Integer> set = new TreeSet<>();
            set.add(nums[i]);
            int t = 0;
            for (int j = i + 1; j < n; j++) {
                Integer fk = set.floor(nums[j]);
                Integer ck = set.ceiling(nums[j]);
                if (fk != null && ck != null && ck - fk > 1) t--;
                if (fk != null && nums[j] - fk > 1) t++;
                if (ck != null && ck - nums[j] > 1) t++;
                res += t;
                set.add(nums[j]);
            }
        }
        return res;
    }
}