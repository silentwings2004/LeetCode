package LC3001_3300;
import java.util.*;
public class LC3233_FindtheCountofNumbersWhichAreNotSpecial {
    /**
     * You are given 2 positive integers l and r. For any number x, all positive divisors of x except x are called the
     * proper divisors of x.
     *
     * A number is called special if it has exactly 2 proper divisors. For example:
     *
     * The number 4 is special because it has proper divisors 1 and 2.
     * The number 6 is not special because it has proper divisors 1, 2, and 3.
     * Return the count of numbers in the range [l, r] that are not special.
     *
     * Input: l = 5, r = 7
     * Output: 3
     *
     * Input: l = 4, r = 16
     * Output: 11
     *
     * Constraints:
     *
     * 1 <= l <= r <= 10^9
     * @param l
     * @param r
     * @return
     */
    // time = O(sqrt(r)), space = O(sqrt(r)),
    HashSet<Integer> set;
    boolean[] st;
    public int nonSpecialCount(int l, int r) {
        set = new HashSet<>();
        int n = (int)Math.sqrt(r);
        st = new boolean[n + 1];
        get_primes(n);
        int res = r - l + 1;
        for (int i = 2; i <= r / i; i++) {
            if (i * i >= l && set.contains(i)) res--;
        }
        return res;
    }

    private void get_primes(int n) {
        for (int i = 2; i <= n; i++) {
            if (!st[i]) {
                set.add(i);
                for (int j = i + i; j <= n; j += i) st[j] = true;
            }
        }
    }
}