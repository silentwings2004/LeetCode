package LC001_300;
import java.util.*;
public class LC231_PowerofTwo {
    /**
     * Given an integer n, return true if it is a power of two. Otherwise, return false.
     *
     * An integer n is a power of two, if there exists an integer x such that n == 2^x.
     *
     * Input: n = 1
     * Output: true
     *
     * Constraints:
     *
     * -2^31 <= n <= 2^31 - 1
     *
     *
     * Follow up: Could you solve it without loops/recursion?
     * @param n
     * @return
     */
    // time = O(1), space = O(1)
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & -n) == n;
    }
}
/**
 * 一个数是2的整次幂的话，它的lowbit必然是等于它本身，即 lowbit(n) == n.
 */