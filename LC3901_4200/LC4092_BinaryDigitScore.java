package LC3901_4200;

public class LC4092_BinaryDigitScore {
    /**
     * You are given two integers a and b.
     *
     * Let s1 and s2 be the number of set bits in the binary representations of a and b, respectively.
     *
     * The score of these integers is defined as follows:
     *
     * If s1 >= s2, the score is s1 * s2.
     * Otherwise, the score is s2 / s1 using integer division.
     * Return an integer representing the score of a and b.
     *
     * A set bit refers to a bit in the binary representation of a number that has a value of 1.
     *
     * Input: a = 14, b = 6
     * Output: 6
     *
     * Input: a = 5, b = 15
     * Output: 2
     *
     * Input: a = 10, b = 12
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= a, b <= 100
     * @param a
     * @param b
     * @return
     */
    // time = O(1), space = O(1)
    public int calculateScore(int a, int b) {
        int s1 = Integer.bitCount(a);
        int s2 = Integer.bitCount(b);
        return s1 >= s2 ? s1 * s2 : s2 / s1;
    }
}