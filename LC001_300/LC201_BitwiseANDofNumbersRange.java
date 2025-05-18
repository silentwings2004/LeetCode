package LC001_300;
import java.util.*;
public class LC201_BitwiseANDofNumbersRange {
    /**
     * Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers
     * in this range, inclusive.
     *
     * Input: left = 5, right = 7
     * Output: 4
     *
     * Constraints:
     *
     * 0 <= left <= right <= 2^31 - 1
     * @param left
     * @param right
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public int rangeBitwiseAnd(int left, int right) {
        int m = left, n = right, res = 0;
        for (int i = 30; i >= 0; i--) {
            if ((m >> i & 1) != (n >> i & 1)) break;
            if ((m >> i & 1) == 1) res += 1 << i;
        }
        return res;
    }

    // S2
    // time = O(1), space = O(1)
    public int rangeBitwiseAnd2(int left, int right) {
        while (right > left) right = right & (right - 1);
        return right;
    }
}
/**
 * m <= n
 * m =   xxxxx 0_1111 >= m
 * n =   xxxxx 1_0000 <= n
 * =>    xxxxx 0
 * 找到从右到左第一位m和n不一样的地方，从这位往右边，一定全都是0，前面都不变
 */