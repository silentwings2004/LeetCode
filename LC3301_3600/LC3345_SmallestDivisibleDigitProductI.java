package LC3301_3600;

public class LC3345_SmallestDivisibleDigitProductI {
    /**
     * You are given two integers n and t. Return the smallest number greater than or equal to n such that the product
     * of its digits is divisible by t.
     *
     * Input: n = 10, t = 2
     * Output: 10
     *
     * Input: n = 15, t = 3
     * Output: 16
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * 1 <= t <= 10
     * @param n
     * @param t
     * @return
     */
    // time = O(logn), space = O(1)
    public int smallestNumber(int n, int t) {
        int res = n;
        while (true) {
            if (get(res) % t == 0) break;
            res++;
        }
        return res;
    }

    private int get(int x) {
        int v = 1;
        while (x > 0) {
            v *= x % 10;
            x /= 10;
        }
        return v;
    }
}
