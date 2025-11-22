package LC3601_3900;

public class LC3740_MinimumDistanceBetweenThreeEqualElementsI {
    /**
     * You are given an integer array nums.
     *
     * A tuple (i, j, k) of 3 distinct indices is good if nums[i] == nums[j] == nums[k].
     *
     * The distance of a good tuple is abs(i - j) + abs(j - k) + abs(k - i), where abs(x) denotes the absolute value of x.
     *
     * Return an integer denoting the minimum possible distance of a good tuple. If no good tuples exist, return -1.
     *
     * Input: nums = [1,2,1,1,3]
     * Output: 6
     *
     * Input: nums = [1,1,2,3,2,1,2]
     * Output: 8
     *
     * Input: nums = [1]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 100
     * 1 <= nums[i] <= n
     * @param nums
     * @return
     */
    // time = O(n^3), space = O(1)
    public int minimumDistance(int[] nums) {
        final int inf = 0x3f3f3f3f;
        int n = nums.length, res = inf;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] != nums[i]) continue;
                for (int k = j + 1; k < n; k++) {
                    if (nums[k] != nums[j]) continue;
                    int d = (j - i) + (k - j) + (k - i);
                    res = Math.min(res, d);
                }
            }
        }
        return res == inf ? -1 : res;
    }
}