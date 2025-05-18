package LC3001_3300;

public class LC3105_LongestStrictlyIncreasingorStrictlyDecreasingSubarray {
    /**
     * You are given an array of integers nums. Return the length of the longest subarray of nums which is either
     * strictly increasing or strictly decreasing.
     *
     * Input: nums = [1,4,3,3,2]
     * Output: 2
     *
     * Input: nums = [3,3,3,3]
     * Output: 1
     *
     * Input: nums = [3,2,1]
     * Output: 3Constraints:
     *
     * 1 <= nums.length <= 50
     * 1 <= nums[i] <= 50
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums[j] > nums[j - 1]) j++;
            res = Math.max(res, j - i);
            i = j - 1;
        }
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums[j] < nums[j - 1]) j++;
            res = Math.max(res, j - i);
            i = j - 1;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int longestMonotonicSubarray2(int[] nums) {
        int res = 1, i = 0, n = nums.length;
        while (i < n - 1) {
            if (nums[i + 1] == nums[i]) {
                i++;
                continue;
            }
            int i0 = i;
            boolean inc = nums[i + 1] > nums[i];
            i += 2;
            while (i < n && nums[i] != nums[i - 1] && (nums[i] > nums[i - 1]) == inc) i++;
            res = Math.max(res, i - i0);
            i--;
        }
        return res;
    }
}