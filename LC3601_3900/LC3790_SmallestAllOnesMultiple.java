package LC3601_3900;
import java.util.*;
public class LC3790_SmallestAllOnesMultiple {
    /**
     * You are given a positive integer k.
     *
     * Find the smallest integer n divisible by k that consists of only the digit 1 in its decimal representation
     * (e.g., 1, 11, 111, ...).
     *
     * Return an integer denoting the number of digits in the decimal representation of n. If no such n exists,
     * return -1.
     *
     * Input: k = 3
     * Output: 3
     *
     * Input: k = 7
     * Output: 6
     *
     * Input: k = 2
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= k <= 10^5
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int minAllOneMultiple(int k) {
        HashSet<Integer> set = new HashSet<>();
        int x = 1 % k;
        while (x > 0 && set.add(x)) x = (x * 10 + 1) % k;
        return x > 0 ? -1 : set.size() + 1;
    }

    // S2
    // time = O(k), space = O(1)
    public int minAllOneMultiple2(int k) {
        if (k % 2 == 0 || k % 5 == 0) return -1;
        int r = 0;
        for (int i = 1; i <= k; i++) {
            r = (r * 10 + 1) % k;
            if (r == 0) return i;
        }
        return -1;
    }
}
/**
 * same as LC1015
 */