package LC2401_2700;

public class LC2413_SmallestEvenMultiple {
    /**
     * Given a positive integer n, return the smallest positive integer that is a multiple of both 2 and n.
     *
     * Input: n = 5
     * Output: 10
     *
     * Input: n = 6
     * Output: 6
     *
     *
     Constraints:

     1 <= n <= 150
     * @param n
     * @return
     */
    // time = O(1), space = O(1)
    public int smallestEvenMultiple(int n) {
        return n % 2 == 1 ? 2 * n : n;
    }
}