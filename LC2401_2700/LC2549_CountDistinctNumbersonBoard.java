package LC2401_2700;
import java.util.*;
public class LC2549_CountDistinctNumbersonBoard {
    /**
     * You are given a positive integer n, that is initially placed on a board. Every day, for 109 days, you perform
     * the following procedure:
     *
     * For each number x present on the board, find all numbers 1 <= i <= n such that x % i == 1.
     * Then, place those numbers on the board.
     * Return the number of distinct integers present on the board after 109 days have elapsed.
     *
     * Note:
     *
     * Once a number is placed on the board, it will remain on it until the end.
     * % stands for the modulo operation. For example, 14 % 3 is 2.
     *
     * Input: n = 5
     * Output: 4
     *
     * Input: n = 3
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * @param n
     * @return
     */
    // S1:
    // time = O(1), space = O(1)
    public int distinctIntegers(int n) {
        if (n == 1) return 1;
        return n - 1;
    }

    // S2: Simulation
    // time = O(n^2), space = O(n)
    public int distinctIntegers2(int n) {
        HashSet<Integer> set = new HashSet<>();
        set.add(n);
        int last = 1;
        while (true) {
            List<Integer> q = new ArrayList<>();
            for (int x : set) {
                for (int i = 1; i <= n; i++) {
                    if (x % i == 1) q.add(i);
                }
            }
            set.addAll(q);
            if (set.size() == last) break;
            last = set.size();
        }
        return set.size();
    }
}
