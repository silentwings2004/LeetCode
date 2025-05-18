package LC3001_3300;

public class LC3099_HarshadNumber {
    /**
     * An integer divisible by the sum of its digits is said to be a Harshad number. You are given an integer x. Return
     * the sum of the digits of x if x is a Harshad number, otherwise, return -1.
     *
     * Input: x = 18
     * Output: 9
     *
     * Input: x = 23
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= x <= 100
     * @param x
     * @return
     */
    // time = O(logn), space = O(1)
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int t = x, s = 0;
        while (t > 0) {
            s += t % 10;
            t /= 10;
        }
        if (x % s == 0) return s;
        return -1;
    }
}