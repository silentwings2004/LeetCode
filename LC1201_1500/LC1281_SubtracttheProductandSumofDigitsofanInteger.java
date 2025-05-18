package LC1201_1500;

public class LC1281_SubtracttheProductandSumofDigitsofanInteger {
    /**
     * Given an integer number n, return the difference between the product of its digits and the sum of its digits.
     *
     * Input: n = 234
     * Output: 15
     *
     * Input: n = 4421
     * Output: 21
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public int subtractProductAndSum(int n) {
        int s = 0, p = 1;
        while (n > 0) {
            int t = n % 10;
            n /= 10;
            s += t;
            p *= t;
        }
        return p - s;
    }
}