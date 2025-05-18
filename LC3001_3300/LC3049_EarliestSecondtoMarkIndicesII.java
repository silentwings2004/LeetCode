package LC3001_3300;
import java.util.*;
public class LC3049_EarliestSecondtoMarkIndicesII {
    /**
     * You are given two 1-indexed integer arrays, nums and, changeIndices, having lengths n and m, respectively.
     *
     * Initially, all indices in nums are unmarked. Your task is to mark all indices in nums.
     *
     * In each second, s, in order from 1 to m (inclusive), you can perform one of the following operations:
     *
     * Choose an index i in the range [1, n] and decrement nums[i] by 1.
     * Set nums[changeIndices[s]] to any non-negative value.
     * Choose an index i in the range [1, n], where nums[i] is equal to 0, and mark index i.
     * Do nothing.
     * Return an integer denoting the earliest second in the range [1, m] when all indices in nums can be marked by
     * choosing operations optimally, or -1 if it is impossible.
     *
     * Input: nums = [3,2,3], changeIndices = [1,3,2,2,2,2,3]
     * Output: 6
     *
     * Input: nums = [0,0,1,2], changeIndices = [1,2,1,2,1,2,1,2]
     * Output: 7
     *
     * Input: nums = [1,2,3], changeIndices = [1,2,3]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 5000
     * 0 <= nums[i] <= 10^9
     * 1 <= m == changeIndices.length <= 5000
     * 1 <= changeIndices[i] <= n
     * @param nums
     * @param changeIndices
     * @return
     */
    // time = O(n + m(logm)^2), space = O(n + m)
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = nums.length, m = changeIndices.length;
        if (n > m) return -1;
        long tot = 0;
        for (int x : nums) tot += x;
        int l = n, r = m;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, changeIndices, mid, tot)) r = mid;
            else l = mid + 1;
        }
        return check(nums, changeIndices, r, tot) ? r : -1;
    }

    private boolean check(int[] nums, int[] changeIndices, int mid, long tot) {
        int[] c = changeIndices.clone();
        int n = nums.length, m = c.length;
        int[] first = new int[n];
        Arrays.fill(first, -1);
        for (int i = 0; i < mid; i++) {
            if (first[c[i] - 1] == -1 && nums[c[i] - 1] != 0) first[c[i] - 1] = i;
            else c[i] = -1;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = mid - 1; i >= 0; i--) {
            if (c[i] == -1) continue;
            pq.offer(nums[c[i] - 1]);
            if (pq.size() > mid - i - pq.size()) pq.poll();
        }
        long s = 0;
        for (int x : pq) s += x;
        return s + (mid - n - pq.size()) >= tot;
    }
}
/**
 * 减1 (慢速复习) => 随意复习
 * 置0 (快速复习) => 涉及到 changeIndices
 * 标记 (考试) => 参加任意一门课程的考试  => 排的越靠后越好
 *
 * 哪件事情最紧急？
 * 先慢速复习，再快速复习，那么前面的慢速复习做别的事情更好
 * 1,3,2,2,2
 * 倒着遍历，如果这一天不是快速复习，那么就和第三题一样，把cnt++
 * 如果遇到快速复习的那天，
 * - 执行快速复习，消耗一天来考试 (前提是 cnt > 0)
 * - 不快速复习：
 *   1. nums[i] = 0
 *   2. nums[i] = 1
 *   3. cnt = 0 无法快速复习
 *   -> 难道意味着这门课程一定要提早地用[慢速复习] 搞定吗？
 *   -> 反悔贪心 (反悔堆)
 *   -> 取一个 原本用快速复习搞定的，且nums[i] 最小的，反悔这门课程的用时(快速复习的一天+考试的一天)
 *   -> 多出来的这2天时间，用来做当前这门课程的快速复习 + 考试
 *
 *  从右往左遍历结束后，检查没有考试的课程，用慢速复习搞定
 *
 * 每个index 第一次出现时做mark
 * => 可能没机会做mark
 * 从后往前扫一遍
 */
