package LC2401_2700;

public class LC2485_FindthePivotInteger {
    /**
     * Given a positive integer n, find the pivot integer x such that:
     *
     * The sum of all elements between 1 and x inclusively equals the sum of all elements between x and n inclusively.
     * Return the pivot integer x. If no such integer exists, return -1. It is guaranteed that there will be at most one
     * pivot index for the given input.
     *
     * Input: n = 8
     * Output: 6
     *
     * Input: n = 1
     * Output: 1
     *
     * Input: n = 4
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * @param n
     * @return
     */
    // time = O(n), space = O(1)
    public int pivotInteger(int n) {
        int sum = (1 + n) * n / 2;
        for (int i = 1; i <= n; i++) {
            int ps = (1 + i) * i / 2;
            if (ps == sum - ps + i) return i;
        }
        return -1;
    }
}
