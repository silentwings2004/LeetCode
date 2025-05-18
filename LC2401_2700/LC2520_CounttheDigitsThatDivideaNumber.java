package LC2401_2700;

public class LC2520_CounttheDigitsThatDivideaNumber {
    /**
     * Given an integer num, return the number of digits in num that divide num.
     *
     * An integer val divides nums if nums % val == 0.
     *
     * Input: num = 7
     * Output: 1
     *
     * Input: num = 121
     * Output: 2
     *
     * Input: num = 1248
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= num <= 10^9
     * num does not contain 0 as one of its digits.
     * @param num
     * @return
     */
    // time = O(logn), space = O(1)
    public int countDigits(int num) {
        int t = num, res = 0;
        while (t > 0) {
            int d = t % 10;
            if (num % d == 0) res++;
            t /= 10;
        }
        return res;
    }
}