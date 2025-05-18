package LC3301_3600;
import java.util.*;
public class LC3551_MinimumSwapstoSortbyDigitSum {
    /**
     * You are given an array nums of distinct positive integers. You need to sort the array in increasing order based
     * on the sum of the digits of each number. If two numbers have the same digit sum, the smaller number appears first
     * in the sorted order.
     *
     * Return the minimum number of swaps required to rearrange nums into this sorted order.
     *
     * A swap is defined as exchanging the values at two distinct positions in the array.
     *
     * Input: nums = [37,100]
     * Output: 1
     *
     * Input: nums = [22,14,33,7]
     * Output: 0
     *
     * Input: nums = [18,43,34,16]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * nums consists of distinct positive integers.
     * @param nums
     * @return
     */
    // S1: dfs
    // time = O(nlogn), space = O(n)
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] a = new int[n][3];
        for (int i = 0; i < n; i++) a[i] = new int[]{nums[i], get(nums[i]), i};
        Arrays.sort(a, (o1, o2) -> o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int[] b = new int[n];
        for (int i = 0; i < n; i++) b[a[i][2]] = i;

        boolean[] st = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (st[i] || b[i] == i) continue;
            int j = i, sz = 0;
            while (!st[j]) {
                st[j] = true;
                j = b[j];
                sz++;
            }
            if (sz > 0) res += sz - 1;
        }
        return res;
    }

    private int get(int x) {
        int t = 0;
        while (x > 0) {
            t += x % 10;
            x /= 10;
        }
        return t;
    }

    // S2: Union Find
    // time = O(nlogU + nlogn), space = O(n), U: max(nums)
    public int minSwaps2(int[] nums) {
        int n = nums.length;
        int[][] a = new int[n][3];
        for (int i = 0; i < n; i++) {
            int t = 0, x = nums[i];
            while (x > 0) {
                t += x % 10;
                x /= 10;
            }
            a[i] = new int[]{t, nums[i], i};
        }
        Arrays.sort(a, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) uf.merge(a[i][2], i);
        return n - uf.cc;
    }

    class UnionFind {
        private final int[] fa; // 代表元
        private final int[] size; // 集合大小
        public int cc; // 连通块个数

        UnionFind(int n) {
            // 一开始有 n 个集合 {0}, {1}, ..., {n-1}
            // 集合 i 的代表元是自己，大小为 1
            fa = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
            }
            size = new int[n];
            Arrays.fill(size, 1);
            cc = n;
        }

        // 返回 x 所在集合的代表元
        // 同时做路径压缩，也就是把 x 所在集合中的所有元素的 fa 都改成代表元
        public int find(int x) {
            // 如果 fa[x] == x，则表示 x 是代表元
            if (fa[x] != x) {
                fa[x] = find(fa[x]); // fa 改成代表元
            }
            return fa[x];
        }

        // 判断 x 和 y 是否在同一个集合
        public boolean isSame(int x, int y) {
            // 如果 x 的代表元和 y 的代表元相同，那么 x 和 y 就在同一个集合
            // 这就是代表元的作用：用来快速判断两个元素是否在同一个集合
            return find(x) == find(y);
        }

        // 把 from 所在集合合并到 to 所在集合中
        // 返回是否合并成功
        public boolean merge(int from, int to) {
            int x = find(from);
            int y = find(to);
            if (x == y) { // from 和 to 在同一个集合，不做合并
                return false;
            }
            fa[x] = y; // 合并集合。修改后就可以认为 from 和 to 在同一个集合了
            size[y] += size[x]; // 更新集合大小（注意集合大小保存在代表元上）
            // 无需更新 size[x]，因为我们不用 size[x] 而是用 size[find(x)] 获取集合大小，但 find(x) == y，我们不会再访问 size[x]
            cc--; // 成功合并，连通块个数减一
            return true;
        }

        // 返回 x 所在集合的大小
        public int getSize(int x) {
            return size[find(x)]; // 集合大小保存在代表元上
        }
    }
}
/**
 * 排序前 [2,3,1]。
 * 排序后 [1,2,3]。
 * 从左到右遍历排序前的数组，依次把元素交换到它排序后的位置：
 * 2 和 3 交换，得到 [3,2,1]
 * 3 和 1 交换，得到 [1,2,3]
 * 1 已经在正确的位置上了。为什么？因为其他 2 个数都在正确位置上了，不可能存在其他目标位置是 1，所以 1 一定无需交换
 * 所以这 3 个数需要 2 次交换操作
 * => 在「排序前的元素下标」与「排序后的元素下标」之间连边，得到一个图
 * 为什么是下标连边而不是元素值连边？因为元素值和下标是一一对应的，下标的范围更小，可以用数组记录
 * 通过上面的例子可知，图中每个大小为 k 连通块（有 k 个点和 k 条边，是个环），需要 k−1 次交换操作，加到答案中
 * 也可以用 n 减去连通块的个数（每个连通块可以少操作一次），即为答案
 * 计算连通块个数，可以用 DFS、BFS、并查集等
 *
 * 变成有序的最小交换次数：
 * 1. 任意位置交换 n - 连通块个数
 * 2. 相邻位置交换 逆序对 树状数组 (8.2) LCR 170
 */