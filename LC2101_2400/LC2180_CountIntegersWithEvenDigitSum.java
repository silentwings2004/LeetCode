package LC2101_2400;

public class LC2180_CountIntegersWithEvenDigitSum {
    /**
     * Given a positive integer num, return the number of positive integers less than or equal to num whose digit sums
     * are even.
     *
     * The digit sum of a positive integer is the sum of all its digits.
     *
     * Input: num = 4
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= num <= 1000
     * @param num
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int countEven(int num) {
        int res = 0;
        for (int i = 1; i <= num; i++) {
            int t = i, sum = 0;
            while (t > 0) {
                sum += t % 10;
                t /= 10;
            }
            if (sum % 2 == 0) res++;
        }
        return res;
    }
}