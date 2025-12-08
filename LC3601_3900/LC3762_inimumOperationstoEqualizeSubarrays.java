package LC3601_3900;
import java.util.*;
public class LC3762_inimumOperationstoEqualizeSubarrays {
    /**
     * You are given an integer array nums and an integer k.
     *
     * In one operation, you can increase or decrease any element of nums by exactly k.
     *
     * You are also given a 2D integer array queries, where each queries[i] = [li, ri].
     *
     * For each query, find the minimum number of operations required to make all elements in the subarray nums[li..ri]
     * equal. If it is impossible, the answer for that query is -1.
     *
     * Return an array ans, where ans[i] is the answer for the ith query.
     *
     * Input: nums = [1,4,7], k = 3, queries = [[0,1],[0,2]]
     * Output: [1,2]
     *
     * Input: nums = [1,2,4], k = 2, queries = [[0,2],[0,0],[1,2]]
     * Output: [-1,0,1]
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 4 × 104
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= 10^9
     * 1 <= queries.length <= 4 × 10^4
     * queries[i] = [li, ri]
     * 0 <= li <= ri <= n - 1
     * @param nums
     * @param k
     * @param queries
     * @return
     */
    // time = O(n + q) * logn, space = O(nlogn)
    public long[] minOperations(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        int[] left = new int[n];
        for (int i = 1; i < n; i++) {
            left[i] = nums[i] % k == nums[i - 1] % k ? left[i - 1] : i;
        }

        // 准备离散化
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        // 构建可持久化线段树
        Node[] tr = new Node[n + 1];
        tr[0] = build(0, n - 1); // 类似前缀和的 s[0] = 0
        for (int i = 0; i < n; i++) {
            int j = Arrays.binarySearch(sorted, nums[i]); // 离散化
            tr[i + 1] = tr[i].add(j, nums[i]); // 新的线段树 = 旧的线段树 + (x 的出现次数 + 1)
        }

        long[] ans = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            if (left[r] > l) {
                ans[i] = -1;
                continue;
            }
            r++; // 改成左闭右开
            int sz = r - l; // 计算区间中位数
            long[] res = tr[r].query(tr[l], sz / 2 + 1);
            long median = sorted[(int)res[0]]; // 离散化后的值 -> 原始值

            // 计算区间所有元素到中位数的距离和
            long totalSum = tr[r].sum - tr[l].sum;
            long cntL = res[1], sumL = res[2];
            long sum = median * cntL - sumL; // 蓝色面积
            sum += totalSum - sumL - median * (sz - cntL); // 绿色面积
            ans[i] = sum / k; // 操作次数 = 距离和 / k
        }
        return ans;
    }

    public Node build(int l, int r) {
        Node o = new Node(l, r);
        if (l == r) return o;
        int mid = l + r >> 1;
        o.lo = build(l, mid);
        o.ro = build(mid + 1, r);
        return o;
    }

    class Node {
        int l, r;
        Node lo, ro;
        int cnt;
        long sum;
        public Node(int l, int r, Node lo, Node ro, int cnt, long sum) {
            this.l = l;
            this.r = r;
            this.lo = lo;
            this.ro = ro;
            this.cnt = cnt;
            this.sum = sum;
        }

        public Node(int l, int r) {
            this(l, r, null, null, 0, 0);
        }

        private void maintain() {
            cnt = lo.cnt + ro.cnt;
            sum = lo.sum + ro.sum;
        }

        // 在线段树的位置 i 添加 val
        public Node add(int i, int val) {
            Node o = new Node(l, r, lo, ro, cnt, sum); // 复制当前节点
            if (l == r) {
                o.cnt++;
                o.sum += val;
                return o;
            }
            int mid = l + r >> 1;
            if (i <= mid) o.lo = o.lo.add(i, val); // o.ro 就在上面的复制中原封不动的保留
            else o.ro = o.ro.add(i, val);
            o.maintain();
            return o;
        }
        // 两棵前缀对应的线段树相减，可以得到询问区间对应的线段树
        public long[] query(Node old, int k) {
            if (l == r) return new long[]{l, 0, 0};
            int cntL = lo.cnt - old.lo.cnt;
            if (k <= cntL) return lo.query(old.lo, k);
            long[] res = ro.query(old.ro, k - cntL);
            res[1] += cntL;
            res[2] += lo.sum - old.lo.sum;
            return res;
        }
    }
}
/**
 * 可持久化线段树
 * 前置知识：单点修改线段树
 * [1,1,2,3,4,2]
 * 值域视角:统计下每个数出现的次数
 * 每个数的出现次数记录在线段树的叶子上
 * 复用一些节点 => 三维线段树
 * 每次新加一个元素，就会新增树高 h 个节点 => O(nlogn) -> 构建 n + 1 棵线段树
 * 核心想法：复用节点信息，O(n^2) 空间压缩成 O(nlogn) 的空间
 */