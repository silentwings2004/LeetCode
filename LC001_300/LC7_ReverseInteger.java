package LC001_300;
import java.util.*;
public class LC7_ReverseInteger {
    /**
     * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside
     * the signed 32-bit integer range [-231, 231 - 1], then return 0.
     *
     * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
     *
     * Input: x = 123
     * Output: 321
     *
     * Input: x = -123
     * Output: -321
     *
     * Constraints:
     *
     * -2^31 <= x <= 2^31 - 1
     * @param x
     * @return
     */
    // time = O(logn) space = O(1)
    public int reverse(int x) {
        int r = 0;
        while (x != 0) {
            if (r > 0 && r > (Integer.MAX_VALUE - x % 10) / 10) return 0;
            if (r < 0 && r < (Integer.MIN_VALUE - x % 10) / 10) return 0;
            r = r * 10 + x % 10;
            x /= 10;
        }
        return r;
    }
}
/**
 * 把每一位抠出来
 *
 * 从个位开始抠
 * x % 10
 * x /= 10
 *
 * r = 0
 * r = r * 10 + 4 = 4
 * r = r * 10 + 3 = 43
 * r = r * 10 + 2 = 432
 * ....
 *
 * 秦九韶算法
 */