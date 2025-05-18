package LC001_300;
import java.util.*;
public class LC53_MaximumSubarray {
    /**
     * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest
     * sum and return its sum.
     * <p>
     * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * Output: 6
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 3 * 10^4
     * -10^5 <= nums[i] <= 10^5
     * <p>
     * <p>
     * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer
     * approach, which is more subtle.
     *
     * @param nums
     * @return
     */
    // S1: DP
    // time = O(n), space = O(1)
    public int maxSubArray(int[] nums) {
        int curSum = 0, res = Integer.MIN_VALUE;
        for (int x : nums) {
            curSum = Math.max(curSum + x, x);
            res = Math.max(res, curSum);
        }
        return res;
    }

    // S2: Divide & Conquer (Segment Tree)
    // time = O(n), space = O(logn)
    public int maxSubArray2(int[] nums) {
        Node t = build(nums, 0, nums.length - 1);
        return t.s;
    }

    private Node build(int[] nums, int l, int r) {
        if (l == r) return new Node(nums[r], nums[r], nums[r], nums[r]);

        int mid = l + r >> 1;
        Node L = build(nums, l, mid);
        Node R = build(nums, mid + 1, r);

        Node res = new Node(0, 0, 0, 0);
        res.sum = L.sum + R.sum;
        res.s = Math.max(Math.max(L.s, R.s), L.rs + R.ls);
        res.ls = Math.max(L.ls, L.sum + R.ls);
        res.rs = Math.max(R.rs, L.rs + R.sum);
        return res;
    }

    private class Node {
        private int sum, s, ls, rs;
        public Node(int sum, int s, int ls, int rs) {
            this.sum = sum;
            this.s = s;
            this.ls = ls;
            this.rs = rs;
        }
    }
}
/**
 * f[i]表示所有以nums[i]结尾的区间中的最大和是多少
 * 状态计算：
 * 1.区间长度 >= 2
 * 2. 区间长度 = 1
 * f[i] = max{nums[i], f[i - 1] + nums[i]} = nums[i] + max{f[i - 1], 0}
 * f[i-1] -> last
 * 滚动
 *
 * S2: 分治
 * 可以变复杂 => 支持修改操作！
 * 先递归下左边，再递归右边
 * 1. 最大子段和
 * 2. 最大前缀
 * 3. 最大后缀
 * 4. 总和
 * ref: AC245 => 线段树
 *
 * Kadane
 * dp[i]: 以i为结尾的subarray sum最大为多少，长度不确定，关注以i结尾
 * 是自成一派，还是接在前面
 * dp[i] = dp[i-1] + nums[i]  (if dp[i-1] < 0 => nums[i]反而会变小)
 * dp[i] = nums[i]
 * dp[i] = Math.max(dp[i-1] + nums[i], nums[i])
 */