package LC601_900;

import java.util.HashMap;

public class LC765_CouplesHoldingHands {
    /**
     * There are n couples sitting in 2n seats arranged in a row and want to hold hands.
     *
     * The people and seats are represented by an integer array row where row[i] is the ID of the person sitting in the
     * ith seat. The couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and
     * so on with the last couple being (2n - 2, 2n - 1).
     *
     * Return the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any
     * two people, then they stand up and switch seats.
     *
     * Input: row = [0,2,1,3]
     * Output: 1
     *
     * Constraints:
     *
     * 2n == row.length
     * 2 <= n <= 30
     * n is even.
     * 0 <= row[i] < 2n
     * All the elements of row are unique.
     * @param row
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[] p;
    public int minSwapsCouples(int[] row) {
        int n = row.length / 2;
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        int cnt = n;
        for (int i = 0; i < n * 2; i += 2) {
            int a = row[i] / 2, b = row[i + 1] / 2;
            if (find(a) != find(b)) {
                p[find(a)] = find(b);
                cnt--;
            }
        }
        return n - cnt;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
/**
 * S1: greedy
 * 每做1次交换，可以让一对couple凑到一块
 * 0 1
 * 2 3
 * 4 5
 * 链式反应
 * k  k-1
 * 6  7
 * 9  8
 * 接下来,我们可以继续在这个数列里寻找下一个没配对的位置,重复上述的过程,在一个闭环中完成若干次匹配.这种算法可以成为cyclic swapping
 *
 * S2: Union Find
 * 01 23 || 45 67 89
 * 02 13    46 78 95
 *  01         02
 * 先把本来是一对couple的连到一起
 * 扫一遍，非couple也union起来
 * 每个group代表这个couple在这个圈子里是互换的
 * 1. 每个union之间是没有关系的，即没有任何couple被拆在不同的union里
 * 2. 在每个union内部，都可以通过若干次的swap使得内部的couple得到配对
 * 3. 每个union不可能再拆分为若干个更小的union
 * 2 couples -> n - 1 次 swap
 * 3 couples -> n - 2 次 swap
 * 有没有可能用更少次数的swap使其配对呢？其实不可能。
 *
 * 点：每对情侣
 * 边：沙发
 *
 * 1. 同属一个环，交换环内 => 多一个环
 * 2. 属于不同环 => 少一个环
 * 每操作一次，最多多一个环 => n - cnt
 * 无向图里，环与连通块等价
 */