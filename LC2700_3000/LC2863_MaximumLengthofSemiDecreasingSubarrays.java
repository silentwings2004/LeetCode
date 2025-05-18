package LC2700_3000;
import java.util.*;
public class LC2863_MaximumLengthofSemiDecreasingSubarrays {
    /**
     * You are given an integer array nums.
     *
     * Return the length of the longest semi-decreasing subarray of nums, and 0 if there are no such subarrays.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     * A non-empty array is semi-decreasing if its first element is strictly greater than its last element.
     *
     * Input: nums = [7,6,5,4,3,2,1,6,10,11]
     * Output: 8
     *
     * Input: nums = [57,55,50,60,61,58,63,59,64,60,63]
     * Output: 6
     *
     * Input: nums = [1,2,3,4]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int maxSubarrayLength(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            Integer hk = map.higherKey(nums[i]);
            if (hk == null) {
                if (!map.containsKey(nums[i])) map.put(nums[i], i);
            } else res = Math.max(res, i - map.get(hk) + 1);
        }
        return res;
    }

    // S2:
    // time = O(nlogn), space = O(n)
    public int maxSubarrayLength2(int[] nums) {
        int n = nums.length;
        int[][] w = new int[n][2];
        for (int i = 0; i < n; i++) w[i] = new int[]{nums[i], i};
        Arrays.sort(w, (o1, o2) -> o1[0] - o2[0]);
        int mp = -1, res = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            int tmp = -1;
            while (j < n && w[j][0] == w[i][0]) {
                if (mp != -1) res = Math.max(res, mp - w[j][1] + 1);
                tmp = Math.max(tmp, w[j][1]);
                j++;
            }
            mp = Math.max(mp, tmp);
            i = j - 1;
        }
        return res;
    }
}