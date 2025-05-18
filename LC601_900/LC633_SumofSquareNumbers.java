package LC601_900;

public class LC633_SumofSquareNumbers {
    /**
     * Given a non-negative integer c, decide whether there're two integers a and b such that a^2 + b^2 = c.
     *
     * Constraints:
     *
     * 0 <= c <= 2^31 - 1
     * @param c
     * @return
     */
    // time = O(sqrt(c), space = O(1)
    public boolean judgeSquareSum(int c) {
        for (long i = 0; i * i <= c; i++) {
            long j = c - i * i;
            long r = (long) Math.sqrt(j);
            if (r * r == j) return true;
        }
        return false;
    }
}