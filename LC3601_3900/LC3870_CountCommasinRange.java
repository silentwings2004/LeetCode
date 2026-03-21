package LC3601_3900;

public class LC3870_CountCommasinRange {
    /**
     * You are given an integer n.
     *
     * Return the total number of commas used when writing all integers from [1, n] (inclusive) in standard number
     * formatting.
     *
     * In standard formatting:
     *
     * A comma is inserted after every three digits from the right.
     * Numbers with fewer than 4 digits contain no commas.
     *
     * Input: n = 1002
     * Output: 3
     *
     * Input: n = 998
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * @param n
     * @return
     */
    // time = O(1), space = O(1)
    public int countCommas(int n) {
        return Math.max(0, n - 1000 + 1);
    }
}