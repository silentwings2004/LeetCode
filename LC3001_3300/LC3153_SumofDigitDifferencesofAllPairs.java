package LC3001_3300;

public class LC3153_SumofDigitDifferencesofAllPairs {
    /**
     * You are given an array nums consisting of positive integers where all integers have the same number of digits.
     *
     * The digit difference between two integers is the count of different digits that are in the same position in the
     * two integers.
     *
     * Return the sum of the digit differences between all pairs of integers in nums.
     *
     * Input: nums = [13,23,12]
     * Output: 4
     *
     * Input: nums = [10,10,10,10]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i] < 10^9
     * All integers in nums have the same number of digits.
     * @param nums
     * @return
     */
    // time = O(n), space = O(100)
    public long sumDigitDifferences(int[] nums) {
        long res = 0;
        int n = nums.length;
        int[][] cnt = new int[10][10];
        for (int x : nums) {
            int i = 0;
            while (x > 0) {
                int v = x % 10;
                x /= 10;
                cnt[i][v]++;
                i++;
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j + 1; k < 10; k++) {
                    int a = cnt[i][j], b = cnt[i][k];
                    res += 1L * a * b;
                }
            }
        }
        return res;
    }
}