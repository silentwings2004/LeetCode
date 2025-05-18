package LC601_900;

public class LC660_Remove9 {
    /**
     * Start from integer 1, remove any integer that contains 9 such as 9, 19, 29...
     *
     * Now, you will have a new integer sequence [1, 2, 3, 4, 5, 6, 7, 8, 10, 11, ...].
     *
     * Given an integer n, return the nth (1-indexed) integer in the new sequence.
     *
     * Input: n = 9
     * Output: 10
     *
     * Input: n = 10
     * Output: 11
     *
     * Constraints:
     *
     * 1 <= n <= 8 * 10^8
     * @param n
     * @return
     */
    // time = O(1), space = O(1)
    public int newInteger(int n) {
        return Integer.parseInt(Integer.toString(n, 9));
    }
}