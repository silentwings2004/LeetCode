package LC301_600;

public class LC477_TotalHammingDistance {
    /**
     * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
     *
     * Given an integer array nums, return the sum of Hamming distances between all the pairs of the integers in nums.
     *
     * Input: nums = [4,14,2]
     * Output: 6
     *
     * Input: nums = [4,14,4]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * 0 <= nums[i] <= 10^9
     * The answer for the given input will fit in a 32-bit integer.
     * @param nums
     * @return
     */
    // time = O(nlogN), space = O(1)
    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for (int i = 0; i < 30; i++) {
            int a = 0, b = 0;
            for (int x : nums) {
                if ((x >> i & 1) == 1) b++;
                else a++;
            }
            res += a * b;
        }
        return res;
    }
}
/**
 * 每一位之间是相互独立的
 * 枚举每一位
 */