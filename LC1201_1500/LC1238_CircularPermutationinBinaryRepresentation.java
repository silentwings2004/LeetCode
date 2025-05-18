package LC1201_1500;
import java.util.*;
public class LC1238_CircularPermutationinBinaryRepresentation {
    /**
     * Given 2 integers n and start. Your task is return any permutation p of (0,1,2.....,2^n -1) such that :
     *
     * p[0] = start
     * p[i] and p[i+1] differ by only one bit in their binary representation.
     * p[0] and p[2^n -1] must also differ by only one bit in their binary representation.
     *
     * Input: n = 2, start = 3
     * Output: [3,2,0,1]
     *
     * Constraints:
     *
     * 1 <= n <= 16
     * 0 <= start < 2 ^ n
     * @param n
     * @param start
     * @return
     */
    // S1
    // time = O(2^n), space = O(2^n)
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> res = new ArrayList<>();
        res.add(0);

        int idx = 0; // res里第一个元素是0，所以start的探索位置就是0，因为这个一上来的0在下面for loop里是探索不到的!
        for (int i = 0; i < n; i++) {
            int len = res.size();
            for (int j = len - 1; j >= 0; j--) {
                res.add(res.get(j) | 1 << i);
                if ((res.get(j) | 1 << i) == start) idx = res.size() - 1;
            }
        }

        List<Integer> ans = new ArrayList<>();
        int m = res.size();
        for (int i = idx; i < idx + m; i++) ans.add(res.get(i % m));
        return ans;
    }

    // S2
    // time = O(2^n), space = O(2^n)
    public List<Integer> circularPermutation2(int n, int start) {
        List<Integer> a = new ArrayList<>();
        a.add(0);
        a.add(1);
        for (int i = 1; i < n; i++) {
            List<Integer> b = new ArrayList<>(a);
            for (int j = a.size() - 1; j >= 0; j--) {
                b.add(a.get(j) + (1 << i));
            }
            a = b;
        }
        for (int i = 0; i < a.size(); i++) a.set(i, a.get(i) ^ start);
        return a;
    }
}
/**
 * ref: LC89 Gray code
 * 每相邻2个数之间只相差1个bit位
 * 00         000
 * 01         001
 * 11         011
 * 10         010
 *            110
 *            111
 *            101
 *            100
 * 镜像边缘diff only by 1 bit
 *
 * 格雷码
 * 构造完之后让每一个数都xor start就能让第一个数变成start
 * 递归来构造
 */