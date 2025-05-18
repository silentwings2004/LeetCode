package LC1201_1500;

public class LC1486_XOROperationinanArray {
    /**
     * You are given an integer n and an integer start.
     *
     * Define an array nums where nums[i] = start + 2 * i (0-indexed) and n == nums.length.
     *
     * Return the bitwise XOR of all elements of nums.
     *
     * Input: n = 5, start = 0
     * Output: 8
     *
     * Input: n = 4, start = 3
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * 0 <= start <= 1000
     * n == nums.length
     * @param n
     * @param start
     * @return
     */
    // time = O(n), space = O(1)
    public int xorOperation(int n, int start) {
        int res = 0;
        for (int i = 0; i < n; i++) res ^= start + 2 * i;
        return res;
    }
}