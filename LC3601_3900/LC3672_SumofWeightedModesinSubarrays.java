package LC3601_3900;
import java.util.*;
public class LC3672_SumofWeightedModesinSubarrays {
    /**
     * You are given an integer array nums and an integer k.
     *
     * For every subarray of length k:
     *
     * The mode is defined as the element with the highest frequency. If there are multiple choices for a mode, the
     * smallest such element is taken.
     * The weight is defined as mode * frequency(mode).
     * Return the sum of the weights of all subarrays of length k.
     *
     * Note:
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     * The frequency of an element x is the number of times it occurs in the array.
     *
     * Input: nums = [1,2,2,3], k = 3
     * Output: 8
     *
     * Input: nums = [1,2,1,2], k = 2
     * Output: 3
     *
     * Input: nums = [4,3,4,3], k = 3
     * Output: 14
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long modeWeight(int[] nums, int k) {
        int n = nums.length;
        long res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        TreeSet<int[]> set = new TreeSet<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1]);
        for (int i = 0, j = 0; i < n; i++) {
            if (i - j + 1 > k) {
                set.remove(new int[]{map.get(nums[j]), nums[j]});
                map.put(nums[j], map.get(nums[j]) - 1);
                if (map.get(nums[j]) > 0) set.add(new int[]{map.get(nums[j]), nums[j]});
                j++;
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            set.add(new int[]{map.get(nums[i]), nums[i]});
            if (i - j + 1 == k) {
                int[] x = set.first();
                res += 1L * x[0] * x[1];
            }
        }
        return res;
    }
}