package LC3601_3900;

public class LC3895_CountDigitAppearances {
    /**
     * You are given an integer array nums and an integer digit.
     *
     * Return the total number of times digit appears in the decimal representation of all elements in nums.
     *
     * Input: nums = [12,54,32,22], digit = 2
     * Output: 4
     *
     * Input: nums = [1,34,7], digit = 9
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^6
     * 0 <= digit <= 9
     * @param nums
     * @param digit
     * @return
     */
    // time = O(nlogk), space = O(1)
    public int countDigitOccurrences(int[] nums, int digit) {
        int res = 0;
        for (int x : nums) res += get(x, digit);
        return res;
    }

    private int get(int x, int t) {
        int cnt = 0;
        while (x > 0) {
            int v = x % 10;
            x /= 10;
            if (v == t) cnt++;
        }
        return cnt;
    }
}