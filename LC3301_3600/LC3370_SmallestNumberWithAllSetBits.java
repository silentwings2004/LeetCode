package LC3301_3600;

public class LC3370_SmallestNumberWithAllSetBits {
    /**
     * You are given a positive number n.
     *
     * Return the smallest number x greater than or equal to n, such that the binary representation of x contains only
     * set bits.
     *
     * A set bit refers to a bit in the binary representation of a number that has a value of 1.
     *
     * Input: n = 5
     * Output: 7
     *
     * Input: n = 10
     * Output: 15
     *
     * Input: n = 3
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * @param n
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public int smallestNumber(int n) {
        int x = n, res = n;
        while (true) {
            if (check(x)) {
                res = x;
                break;
            }
            x++;
        }
        return res;
    }

    private boolean check(int x) {
        while (x > 0) {
            int u = x & 1;
            if (u == 0) return false;
            x >>= 1;
        }
        return true;
    }

    // S2
    // time = O(1), space = O(1)
    public int smallestNumber2(int n) {
        int m = 32 - Integer.numberOfLeadingZeros(n);
        return (1 << m) - 1;
    }
}