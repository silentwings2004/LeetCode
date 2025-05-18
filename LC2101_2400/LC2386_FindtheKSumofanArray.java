package LC2101_2400;
import java.util.*;
public class LC2386_FindtheKSumofanArray {
    /**
     * You are given an integer array nums and a positive integer k. You can choose any subsequence of the array and
     * sum all of its elements together.
     *
     * We define the K-Sum of the array as the kth largest subsequence sum that can be obtained (not necessarily distinct).
     *
     * Return the K-Sum of the array.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
     * the order of the remaining elements.
     *
     * Note that the empty subsequence is considered to have a sum of 0.
     *
     * Input: nums = [2,4,-2], k = 5
     * Output: 2
     *
     * Input: nums = [1,-2,3,4,-10,12], k = 16
     * Output: 10
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * 1 <= k <= min(2000, 2^n)
     * @param nums
     * @param k
     * @return
     */
    // S1: PQ
    // time = O(nlogn), space = O(n)
    public long kSum(int[] nums, int k) {
        int n = nums.length;
        long maxSum = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) maxSum += nums[i];
            else nums[i] = -nums[i];
        }
        if (k == 1) return maxSum;
        Arrays.sort(nums);

        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0])); // {sum, id}
        pq.offer(new long[]{nums[0], 0});
        long sum = 0;
        for (int t = 0; t < k - 1; t++) {
            long[] x = pq.poll();
            sum = x[0];
            int i = (int) x[1];
            if (t == k - 2) break;
            if (i + 1 < n) {
                pq.offer(new long[]{sum - nums[i] + nums[i + 1], i + 1});
                pq.offer(new long[]{sum + nums[i + 1], i + 1});
            }
        }
        return maxSum - sum;
    }

    // S2: 多路归并
    // time = O(nlogn + k^2), space = O(n)
    public long kSum2(int[] nums, int k) {
        long s = 0;
        List<Integer> q = new ArrayList<>();
        for (int x : nums) {
            if (x >= 0) {
                s += x;
                q.add(-x);
            } else q.add(x);
        }
        Collections.sort(q, (o1, o2) -> o2 - o1);
        while (q.size() > k) q.remove(q.size() - 1);

        List<Long> a = new ArrayList<>();
        a.add(s);

        int t = 1;
        for (int x : q) {
            int i = 0, j = 0;
            t = Math.min(t * 2, k);
            List<Long> b = new ArrayList<>();
            while (i < a.size() && j < a.size() && b.size() < t) {
                if (a.get(i) > a.get(j) + x) b.add(a.get(i++));
                else b.add(a.get(j++) + x);
            }
            while (i < a.size() && b.size() < t) b.add(a.get(i++));
            while (j < a.size() && b.size() < t) b.add(a.get(j++) + x);
            a = new ArrayList<>(b);
        }
        return a.get(k - 1);
    }
}
/**
 * 多路归并算法
 * 只用保留最大的k个数，只需要归并出来前k个
 * 每个数枚举一遍
 * 倒着做，先算下最大的和，把所有正数相加
 * 要把总和变小，变小的话就要和最大和做差值
 * => 从剩下的数里凑出最小的k个数，要么+负数，要么-正数
 * 从小到大排序，最多只会选前k个数 => O(nk) = O(k^2)
 *
 * 显然，本题最大sum的序列S就是将数组里所有的正数都取出来，对应的是MaxSum。
 * 次大的sum值必然是在这个序列S的基础上减去一个已经存在的正数，或者加上一个还未入队的负数。
 * 第k大的sum值也必然是从这个序列S里减去若干个已经存在的正数，或者加上若干个还未入队的负数。
 * 因为减去正数等于减去它的绝对值，加上负数也等于减去它的绝对值，
 * 所以第k大的sum值，等价于在S（即MaxSum）的基础上减去若干个nums里元素的绝对值。
 * 故我们只要在nums的绝对值数组里挑出第k小的序列和即可。
 *
 * 空集必然对应最小的序列和，我们单独处理。
 * 对数组进行排序。
 * 将{nums[0], 0}放入一个小顶堆中（即优先弹出最小值的优先队列）。
 * 如果从PQ里弹出的是{sum, i}（其中sum必然是某个以nums[i]结尾的子序列之和），
 * 那么将{sum-nums[i]+nums[i+1], i+1}和{sum+nums[i+1], i+1}加入PQ中（记做操作1和操作2）
 * PQ里第k-1个弹出的sum就是该正数数组里第k大的序列和
 *
 * 证明这个构造方法的正确性
 * 1. 我们要证明这个构造方法覆盖了所有的子序列。
 * x x x nums[i]
 *    nums[k]    k+1 -> k+2 -> k+3...-> i  以i结尾
 * 2. 我们要证明这个构造方法不会产生重复的子序列。最后一步变换要么是操作1，要么是操作2.
 * 3. 我们要证明这个构造方法生成的子序列是按照和递增的。
 * 假设某个序列A小于序列B，但是B先从队列里弹出，这可能吗？
 * 注意，这意味着B在队列里的时候A一定还没有加入队列里（否则PQ会优先弹出A）。
 * 既然A不在队列里，说明A的前驱状态A'（上一段证明了存在唯一的A'）也必然不会在队列里，
 * 因为A'是小于A的，A'在队列的话更会比B优先弹出，从而导致将A也被导入队列里。
 * 同理证明，A'的前驱序列A''也不会在队列里，A''的前驱序列也不会在队列里...
 * 但是所有的序列都是从{nums[0]}开始的，难道这个序列也从未加入过队列吗？从而引发矛盾。
 * 综上，我们证明了这种构造方法一定会一个不漏、一个也不重复地、按从小到大的顺序弹出所有的子序列之和。
 * 显然第k-1个弹出来的就是第k小的子序列之和（考虑空集）。
 */