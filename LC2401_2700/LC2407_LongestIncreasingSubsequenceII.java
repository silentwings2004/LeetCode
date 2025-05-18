package LC2401_2700;
import java.util.*;
public class LC2407_LongestIncreasingSubsequenceII {
    /**
     * You are given an integer array nums and an integer k.
     *
     * Find the longest subsequence of nums that meets the following requirements:
     *
     * The subsequence is strictly increasing and
     * The difference between adjacent elements in the subsequence is at most k.
     * Return the length of the longest subsequence that meets the requirements.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
     * the order of the remaining elements.
     *
     * Input: nums = [4,2,1,4,3,4,5,8,15], k = 3
     * Output: 5
     *
     * Input: nums = [7,4,5,1,8,12,4,7], k = 5
     * Output: 4
     *
     * Input: nums = [1,5], k = 1
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i], k <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int lengthOfLIS(int[] nums, int k) {
        int max = nums[0];
        for (int x : nums) max = Math.max(max, x);
        SegTreeNode root = new SegTreeNode(0, max, 0);

        int res = 0;
        for (int x : nums) {
            int len = root.queryRange(Math.max(0, x - k), Math.max(0, x - 1));
            root.updateRange(x, x, len + 1);
            res = Math.max(res, len + 1);
        }
        return res;
    }

    private class SegTreeNode {
        private SegTreeNode left, right;
        private int start, end, info;
        private boolean tag;
        public SegTreeNode(int a, int b, int val) {
            start = a;
            end = b;
            if (a == b) {
                info = val;
                return;
            }
            int mid = a + b >> 1;
            if (left == null) {
                left = new SegTreeNode(a, mid, val);
                right = new SegTreeNode(mid + 1, b, val);
                info = Math.max(left.info, right.info);
            }
        }

        private void pushDown() {
            if (tag && left != null) {
                left.info = info;
                right.info = info;
                left.tag = true;
                right.tag = true;
                tag = false;
            }
        }

        private void updateRange(int a, int b, int val) {
            if (b < start || a > end) return;
            if (a <= start && end <= b) {
                info = val;
                tag = true;
                return;
            }

            if (left != null) {
                pushDown();
                left.updateRange(a, b, val);
                right.updateRange(a, b, val);
                info = Math.max(left.info, right.info);
            }
        }

        private int queryRange(int a, int b) {
            if (b < start || a > end) return Integer.MIN_VALUE;
            if (a <= start && end <= b) return info;
            if (left != null) {
                pushDown();;
                int res = Math.max(left.queryRange(a, b), right.queryRange(a, b));
                info = Math.max(left.info, right.info);
                return res;
            }
            return info;
        }
    }
}
/**
 * 切入点想法：dp
 * [x x x x x j x x] i
 * nums[i] = x
 * nums[j] = y s.t. y ~ [x-k, x-1]
 * dp[i] = max(dp[j]+1}
 * 从数值上考虑 => nums[i] ~ 10^5
 * 从值域上去考虑，y是在一个区间里
 * dp[x] = max{dp[y] + 1}  y在一个区间里 y ~ [x-k, x-1]
 * 1. segment tree
 * 2. sliding window maximum -> deque  取决于x是否单调，但这里x也是忽大忽小的
 * => segment tree
 * 构造一个线段树，里面的每个叶子结点，以数值结尾的LIS长度
 * 索引域 -> 值域
 */