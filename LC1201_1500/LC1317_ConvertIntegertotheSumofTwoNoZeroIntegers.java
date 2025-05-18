package LC1201_1500;

public class LC1317_ConvertIntegertotheSumofTwoNoZeroIntegers {
    /**
     * No-Zero integer is a positive integer that does not contain any 0 in its decimal representation.
     *
     * Given an integer n, return a list of two integers [a, b] where:
     *
     * a and b are No-Zero integers.
     * a + b = n
     * The test cases are generated so that there is at least one valid solution. If there are many valid solutions,
     * you can return any of them.
     *
     * Input: n = 2
     * Output: [1,1]
     *
     * Input: n = 11
     * Output: [2,9]
     *
     * Constraints:
     *
     * 2 <= n <= 10^4
     * @param n
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int[] getNoZeroIntegers(int n) {
        int[] res = new int[2];
        for (int i = 1; i < n; i++) {
            String a = String.valueOf(i), b = String.valueOf(n - i);
            if (a.indexOf('0') == -1 && b.indexOf('0') == -1) {
                res = new int[]{i, n - i};
                break;
            }
        }
        return res;
    }
}