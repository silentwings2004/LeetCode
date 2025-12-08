package LC3601_3900;

public class LC3752_LexicographicallySmallestNegatedPermutationthatSumstoTarget {
    /**
     * You are given a positive integer n and an integer target.
     *
     * Return the lexicographically smallest array of integers of size n such that:
     *
     * The sum of its elements equals target.
     * The absolute values of its elements form a permutation of size n.
     * If no such array exists, return an empty array.
     *
     * An array a is lexicographically smaller than an array b if in the first position where a and b differ, array a
     * has an element that is less than the corresponding element in b.
     *
     * A permutation of size n is a rearrangement of integers 1, 2, ..., n.
     *
     * Input: n = 3, target = 0
     * Output: [-3,1,2]
     *
     * Input: n = 1, target = 10000000000
     * Output: []
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * -10^10 <= target <= 10^10
     * @param n
     * @param target
     * @return
     */
    // time = O(n), space = O(1)
    public int[] lexSmallestNegatedPerm(int n, long target) {
        long s = (1L + n) * n / 2, d = target - s;
        if (d % 2 != 0) return new int[0];
        long ns = d / 2;
        int[] res = new int[n];
        int i = 0, j = n - 1;
        for (int v = n; v >= 1; v--) {
            if (ns <= -v) {
                ns += v;
                res[i++] = -v;
            } else res[j--] = v;
        }
        return ns == 0 ? res : new int[0];
    }
}
/**
 * posS + negS = s
 * posS - negS = t
 * negS = (s - t) / 2
 */