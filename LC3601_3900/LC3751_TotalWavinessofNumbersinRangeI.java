package LC3601_3900;

public class LC3751_TotalWavinessofNumbersinRangeI {
    /**
     * You are given two integers num1 and num2 representing an inclusive range [num1, num2].
     *
     * The waviness of a number is defined as the total count of its peaks and valleys:
     *
     * A digit is a peak if it is strictly greater than both of its immediate neighbors.
     * A digit is a valley if it is strictly less than both of its immediate neighbors.
     * The first and last digits of a number cannot be peaks or valleys.
     * Any number with fewer than 3 digits has a waviness of 0.
     * Return the total sum of waviness for all numbers in the range [num1, num2].
     *
     * Input: num1 = 120, num2 = 130
     * Output: 3
     *
     * Input: num1 = 198, num2 = 202
     * Output: 3
     *
     * Input: num1 = 4848, num2 = 4848
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= num1 <= num2 <= 10^5
     * @param num1
     * @param num2
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int totalWaviness(int num1, int num2) {
        int res = 0;
        for (int i = num1; i <= num2; i++) {
            String s = String.valueOf(i);
            int m = s.length();
            for (int j = 1; j < m - 1; j++) {
                char a = s.charAt(j - 1), b = s.charAt(j), c = s.charAt(j + 1);
                if (b > a && b > c || b < a && b < c) res++;
            }
        }
        return res;
    }
}