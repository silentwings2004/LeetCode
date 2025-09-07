package LC3601_3900;
import java.util.*;
public class LC3666_MinimumOperationstoEqualizeBinaryString {
    /**
     * You are given a binary string s, and an integer k.
     *
     * In one operation, you must choose exactly k different indices and flip each '0' to '1' and each '1' to '0'.
     *
     * Return the minimum number of operations required to make all characters in the string equal to '1'. If it is not
     * possible, return -1.
     *
     * Input: s = "110", k = 1
     * Output: 1
     *
     * Input: s = "0101", k = 3
     * Output: 2
     *
     * Input: s = "101", k = 2
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s[i] is either '0' or '1'.
     * 1 <= k <= s.length
     * @param s
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int minOperations(String s, int k) {
        int n = s.length();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        TreeSet<Integer>[] st = new TreeSet[2];
        for (int i = 0; i < 2; i++) st[i] = new TreeSet<>();
        for (int i = 0; i <= n; i++) st[i & 1].add(i);

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') cnt++;
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(cnt);
        dist[cnt] = 0;
        st[cnt & 1].remove(cnt);

        while (!q.isEmpty()) {
            int t = q.poll();
            int l = Math.min(k, t), r = Math.min(k, n - t);
            l = (k - l) - l;
            r = r - (k - r);

            TreeSet<Integer> set = st[(t + l) & 1];
            Integer ck = set.ceiling(t + l);
            while (ck != null && ck <= t + r) {
                q.offer(ck);
                dist[ck] = dist[t] + 1;
                set.remove(ck);
                ck = set.ceiling(t + l);
            }
        }
        return dist[0];
    }

    // S2: Math
    // time = O(n), space = O(1)
    public int minOperations2(String s, int k) {
        int n = s.length(), z = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') z++;
        }
        if (z == 0) return 0;
        if (n == k) return z == n ? 1 : -1;

        int res = Integer.MAX_VALUE;

        // 情况一：操作次数 m 是偶数
        if (z % 2 == 0) { // z 必须是偶数
            int m = Math.max((z + k - 1) / k, (z + n - k - 1) / (n - k));
            res = m + m % 2; // 把 m 往上调整为偶数
        }

        // 情况二：操作次数 m 是奇数
        if (z % 2 == k % 2) {   // z 和 k 的奇偶性必须相同
            int m = Math.max((z + k - 1) / k, (n - z + n - k -1) / (n - k));
            res = Math.min(res, m | 1); // 把 m 往上调整为奇数
        }
        return res < Integer.MAX_VALUE ? res : -1;
    }
}
/**
 * n 是 s 的长度
 * z 是 s 中 ‘0’ 的个数
 * k 是每次操作翻转的下标个数
 * 设操作 m 次, 一共会翻转 mk 个下标
 *
 * 计算 m 的下界
 *
 * s = 000011111
 * z = 4, ones = n - z = 5
 * 1. mk - z 必须是偶数
 * 2. mk >= z => m >= ceil(z / k)
 * 3.1  m 是偶数
 * z 必须是偶数
 * 比如 m = 6，那么由于 '0' 必须翻转奇数次，所以'0'的翻转次数至多为 m - 1
 * '1' 至多 m 次
 * z * (m - 1) + (n - z) * m >= mk
 * m >= ceil(z / (n - k))
 * m >= max(ceil(z / k), ceil(z / (n - k))
 * 3.2  m 是奇数
 * k, z 的奇偶性相同
 * 由于'1'必须翻转偶数次，所以'1'的翻转次数至多为 m - 1
 * '0' 至多 m 次
 *  z * m + (n - z) * (m - 1) >= mk
 *  m >= ceil((n - z) / (n - k))
 *  m >= max(ceil(z / k), ceil((n - z) / (n - k))
 *  Gale-Ryser 定理
 *  图论(二分图) / 组合矩阵论
 */