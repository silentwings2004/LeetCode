package LC001_300;
import java.util.*;
public class LC202_HappyNumber {
    /**
     * Write an algorithm to determine if a number n is happy.
     *
     * A happy number is a number defined by the following process:
     *
     * Starting with any positive integer, replace the number by the sum of the squares of its digits.
     * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does
     * not include 1.
     * Those numbers for which this process ends in 1 are happy.
     * Return true if n is a happy number, and false if not.
     *
     * Input: n = 19
     * Output: true
     *
     * Constraints:
     *
     * 1 <= n <= 2^31 - 1
     * @param n
     * @return
     */
    // S1: slow fast pointers
    // time = O(logn), space = O(1)
    public boolean isHappy(int n) {
        int slow = n, fast = get(n);
        while (slow != fast) {
            slow = get(slow);
            fast = get(get(fast));
        }
        return fast == 1;
    }

    private int get(int n) {
        int res = 0;
        while (n > 0) {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res;
    }

    // S2: set
    // time = O(logn), space = O(1)
    public boolean isHappy2(int n) {
        HashSet<Integer> set = new HashSet<>();
        int sum = 0, remain = 0;

        while (set.add(n)) {
            sum = 0;
            while (n > 0) {
                remain = n % 10;
                sum += remain * remain;
                n /= 10;
            }
            if (sum == 1) return true;
            else n = sum;
        }
        return false;
    }
}
/**
 * 每次操作完之后，必定是0~810之间的一个数
 * 最后会转到一个圈里，问结束的圈里是否有1
 * 怎么去遍历这个圈
 * 0~810 共811个数，到812步时一定有重合
 */