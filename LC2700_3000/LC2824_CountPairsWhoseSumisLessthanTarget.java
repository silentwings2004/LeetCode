package LC2700_3000;
import java.util.*;
public class LC2824_CountPairsWhoseSumisLessthanTarget {
    /**
     * Given a 0-indexed integer array nums of length n and an integer target, return the number of pairs (i, j) where
     * 0 <= i < j < n and nums[i] + nums[j] < target.
     *
     * Input: nums = [-1,1,2,3,1], target = 2
     * Output: 3
     *
     * Input: nums = [-6,2,5,-2,-7,-1,3], target = -2
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= nums.length == n <= 50
     * -50 <= nums[i], target <= 50
     * @param nums
     * @param target
     * @return
     */
    // S1
    // time = O(n^2), space = O(1)
    public int countPairs(List<Integer> nums, int target) {
        int n = nums.size(), res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums.get(i) + nums.get(j) < target) res++;
            }
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(logn)
    public int countPairs2(List<Integer> nums, int target) {
        Collections.sort(nums);
        int res = 0, n = nums.size();
        for (int i = 0, j = n - 1; i < j; i++) {
            while (nums.get(i) + nums.get(j) >= target && j > i) j--;
            res += j - i;
        }
        return res;
    }
}