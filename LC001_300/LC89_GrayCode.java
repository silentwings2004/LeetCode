package LC001_300;
import java.util.*;
public class LC89_GrayCode {
    /**
     * An n-bit gray code sequence is a sequence of 2n integers where:
     *
     * Every integer is in the inclusive range [0, 2n - 1],
     * The first integer is 0,
     * An integer appears no more than once in the sequence,
     * The binary representation of every pair of adjacent integers differs by exactly one bit, and
     * The binary representation of the first and last integers differs by exactly one bit.
     * Given an integer n, return any valid n-bit gray code sequence.
     *
     * Input: n = 2
     * Output: [0,1,3,2]
     *
     * Input: n = 1
     * Output: [0,1]
     *
     * Constraints:
     *
     * 1 <= n <= 16
     * @param n
     * @return
     */
    // S1
    // time = O(2^n), space = O(1)
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        // corner case
        if (n == 0) return res;

        for (int i = 0; i < n; i++) {
            int len = res.size();
            for (int j = len - 1; j >= 0; j--) {
                res.add(res.get(j) | (1 << i));
            }
        }
        return res;
    }

    // S2
    // time = O(2^n), space = O(1)
    public List<Integer> grayCode2(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        while (n-- > 0) {
            for (int i = res.size() - 1; i >= 0; i--) {
                int t = res.get(i);
                res.set(i, t << 1);
                res.add((t << 1) + 1);
            }
        }
        return res;
    }
}
/**
 * 格雷码：每2个相邻之间只有1个bit位不同
 * 关于格雷码的构造
 * 镜像法: 逆序排列一下
 * 上半部分继承原来的，下半部分镜像翻转下，然后在前面加1
 * 边界处就靠最高位0和1进行区分
 * 0      00       000
 * 1      01       001
 *        11       011
 *        10       010
 *                 110
 *                 111
 *                 101
 *                 100
 *
 * S2: 通过递归来生成
 * n = 0       0
 * n = 1       0
 *             1
 * n = 2       0 0
 *             1 0
 *             1 1
 *             0 1
 *
 * n = k
 * 2^(k-1)
 * 2^(k-1)
 */
