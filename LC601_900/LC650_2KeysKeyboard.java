package LC601_900;

public class LC650_2KeysKeyboard {
    /**
     * There is only one character 'A' on the screen of a notepad. You can perform one of two operations on this notepad
     * for each step:
     *
     * Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
     * Paste: You can paste the characters which are copied last time.
     * Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the
     * screen.
     *
     * Input: n = 3
     * Output: 3
     *
     * Input: n = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * @param n
     * @return
     */
    // time = O(sqrt(n)), space = O(1)
    public int minSteps(int n) {
        int res = 0;
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                res += i;
                n /= i;
            }
        }
        if (n > 1) res += n;
        return res;
    }
}
/**
 * 每一步操作必然是1个操作1和若干个操作2
 * 分成若干段，x1, x2, ..., xk
 * n = x1 * x2 * ... * xk
 * 最优解就是n的因式分解
 */