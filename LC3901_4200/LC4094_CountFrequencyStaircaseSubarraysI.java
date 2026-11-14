package LC3901_4200;
import java.util.*;
public class LC4094_CountFrequencyStaircaseSubarraysI {
    /**
     * You are given an integer array nums.
     *
     * Consider the distinct values of a subarray in the order in which they first appear.
     *
     * If there are k distinct values, the subarray is called a frequency staircase if the first distinct value occurs
     * exactly once, the second distinct value occurs exactly twice, and so on, with the kth distinct value occurring
     * exactly k times.
     *
     * Equivalently, the frequencies of the distinct values, in the order of their first occurrence, must be exactly
     * 1, 2, 3, ..., k.
     *
     * Return the number of frequency staircase subarrays in nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * The frequency of a value in a subarray is the number of times it occurs in that subarray.
     *
     * Input: nums = [5,8,8,2,2,2]
     * Output: 9
     *
     * Input: nums = [6,6,9,9]
     * Output: 5
     *
     * Input: nums = [2,3,4,4,3,4]
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1500
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(n)
    public int countStaircase(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            HashMap<Integer, Integer> pos = new HashMap<>();
            int cnt = 0;
            for (int j = i; j < n; j++) {
                int x = nums[j];
                if (map.containsKey(x)) map.put(x, map.get(x) + 1);
                else {
                    map.put(x, 1);
                    int m = pos.size();
                    pos.put(x, m + 1);
                }
                if (map.get(x) == pos.get(x)) cnt++;
                else if (map.get(x) > pos.get(x)) break;
                int k = pos.size();
                if (k * (1 + k) / 2 == j - i + 1 && cnt == k) res++;
            }
        }
        return res;
    }
}