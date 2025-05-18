package LC3001_3300;

public class LC3068_FindtheMaximumSumofNodeValues {
    /**
     * There exists an undirected tree with n nodes numbered 0 to n - 1. You are given a 0-indexed 2D integer array
     * edges of length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the
     * tree. You are also given a positive integer k, and a 0-indexed array of non-negative integers nums of length n,
     * where nums[i] represents the value of the node numbered i.
     *
     * Bogdan wants the sum of values of tree nodes to be maximum, for which Bogdan can perform the following operation
     * any number of times (including zero) on the tree:
     *
     * Choose any edge [u, v] connecting the nodes u and v, and update their values as follows:
     * nums[u] = nums[u] XOR k
     * nums[v] = nums[v] XOR k
     * Return the maximum possible sum of the values Bogdan can achieve by performing the operation any number of times.
     *
     * Input: nums = [1,2,1], k = 3, edges = [[0,1],[0,2]]
     * Output: 6
     *
     * Input: nums = [2,3], k = 7, edges = [[0,1]]
     * Output: 9
     *
     * Input: nums = [7,7,7,7,7,7], k = 3, edges = [[0,1],[0,2],[0,3],[0,4],[0,5]]
     * Output: 42
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 2 * 10^4
     * 1 <= k <= 10^9
     * 0 <= nums[i] <= 10^9
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= edges[i][0], edges[i][1] <= n - 1
     * The input is generated such that edges represent a valid tree.
     * @param nums
     * @param k
     * @param edges
     * @return
     */
    // S1: dp
    // time = O(n), space = O(n)
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        long[][] f = new long[n][2];
        f[0][0] = nums[0];
        f[0][1] = nums[0] ^ k;
        for (int i = 1; i < n; i++) {
            f[i][0] = Math.max(f[i - 1][0] + nums[i], f[i - 1][1] + (nums[i] ^ k));
            f[i][1] = Math.max(f[i - 1][1] + nums[i], f[i - 1][0] + (nums[i] ^ k));
        }
        return f[n - 1][0];
    }

    // S2: dp
    // time = O(n), space = O(1)
    public long maximumValueSum2(int[] nums, int k, int[][] edges) {
        long f0 = 0, f1 = (long)-1e18;
        for (int x : nums) {
            long t = Math.max(f0 + x, f1 + (x ^ k));
            f1 = Math.max(f1 + x, f0 + (x ^ k));
            f0 = t;
        }
        return f0;
    }
}
/**
 * 1. 对一条路径上的边都操作一次
 * => 1-2-3-4
 *    1 0 0 1
 * => 只把路径上的起点和终点异或了 k, 其余中间节点不变
 * => 操作可以作用在任意一对 nums[i] 上
 * 2. 被操作的2个数，可以分为哪些情况？
 * 两数都没有异或 k => 多了2个异或 k 的数
 * 两数都异或了 k => 少了2个异或 k 的数
 * 其中一个异或了 k, 领一个没有异或 k => -1+1 = 0 异或 k 的数不变
 * 无论操作多少次，总是有偶数个数异或了 k ！
 * 3. 问题变成：
 * 从 nums 中，选择偶数个数，异或 k, 得到最大元素和是多少？
 * 4. 状态机：
 * 每个元素独立考虑是否要异或 k
 * 0 表示当前有偶数个数异或了 k
 * 1 表示当前有奇数个数异或了 k
 * 定义 f[i][0] 表示从前 i 个数中选偶数个数，异或 k, 得到的最大元素和
 * 定义 f[i][1] 表示从前 i 个数中选奇数个数，异或 k, 得到的最大元素和
 * 初始值
 * f[0][0] = 0
 * f[0][1] = -inf
 * f[i+1][0] = max(f[i][0] + x, f[i][1] + (x ^ k))
 * f[i+1][1] = max(f[i][1] + x, f[i][0] + (x ^ k))
 * 答案：f[n][0]
 */