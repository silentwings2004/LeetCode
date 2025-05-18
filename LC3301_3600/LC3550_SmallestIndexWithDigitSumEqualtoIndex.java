package LC3301_3600;

public class LC3550_SmallestIndexWithDigitSumEqualtoIndex {
    /**
     * You are given an integer array nums.
     *
     * Return the smallest index i such that the sum of the digits of nums[i] is equal to i.
     *
     * If no such index exists, return -1.
     *
     * Input: nums = [1,3,2]
     * Output: 2
     *
     * Input: nums = [1,10,11]
     * Output: 1
     *
     * Input: nums = [1,2,3]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(nlogk), space = O(1)
    public int smallestIndex(int[] nums) {
        int n = Math.min(nums.length, 28);
        for (int i = 0; i < n; i++) {
            if (get(nums[i]) == i) return i;
        }
        return -1;
    }

    private int get(int x) {
        int t = 0;
        while (x > 0) {
            t += x % 10;
            x /= 10;
        }
        return t;
    }
}
/**
 * 0 <= nums[i] <= 1000 => 根据本题的数据范围，数位和最大为 999 的数位和，即 3×9=27
 * 所以枚举到 i=27 即可
 */