package LC2700_3000;

public class LC2749_MinimumOperationstoMaketheIntegerZero {
    /**
     * You are given two integers num1 and num2.
     *
     * In one operation, you can choose integer i in the range [0, 60] and subtract 2^i + num2 from num1.
     *
     * Return the integer denoting the minimum number of operations needed to make num1 equal to 0.
     *
     * If it is impossible to make num1 equal to 0, return -1.
     *
     * Input: num1 = 3, num2 = -2
     * Output: 3
     *
     * Input: num1 = 5, num2 = 7
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= num1 <= 10^9
     * -10^9 <= num2 <= 10^9
     * @param num1
     * @param num2
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public int makeTheIntegerZero(int num1, int num2) {
        int i = 0;
        for (i = 1;; i++) {
            long x = num1 - (long)num2 * i;
            int cnt = 0;
            if (x <= 0) return -1;
            if (x % 2 == 1) {
                x--;
                cnt++;
            }
            int t = Long.bitCount(x);
            if (cnt + t <= i && cnt + x >= i) break;
        }
        return i;
    }

    // S2:
    // time = O(1), space = O(1)
    public int makeTheIntegerZero2(int num1, int num2) {
        for (int i = 1; i <= 36; i++) {
            long x = num1 - (long)num2 * i;
            if (x < i) return -1;
            if (i >= Long.bitCount(x)) return i;
        }
        return -1;
    }
}