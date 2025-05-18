package LC601_900;
import java.util.*;
public class LC795_NumberofSubarrayswithBoundedMaximum {
    /**
     * We are given an array nums of positive integers, and two positive integers left and right (left <= right).
     *
     * Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that
     * subarray is at least left and at most right.
     *
     * Input:
     * nums = [2, 1, 4, 3]
     * left = 2
     * right = 3
     * Output: 3
     *
     * Note:
     *
     * left, right, and nums[i] will be an integer in the range [0, 10^9].
     * The length of nums will be in the range of [1, 50000].
     * @param nums
     * @param left
     * @param right
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int n = nums.length, res = 0, cnt = 0;
        for (int i = 0, j = 0; i < n; i++) {
            if (nums[i] > right) j = i + 1;
            if (nums[i] >= left) cnt = i - j + 1;
            res += cnt;
        }
        return res;
    }

    // S2: monotonic stack
    // time = O(n), space = O(n)
    final int N = 100010;
    public int numSubarrayBoundedMax2(int[] nums, int left, int right) {
        int n = nums.length, tt = 0;
        int[] stk = new int[N];
        int[] prevGreater = new int[n];
        int[] nextGreater = new int[n];
        Arrays.fill(prevGreater, -1);
        Arrays.fill(nextGreater, n);

        for (int i = 0; i < n; i++) {
            while (tt > 0 && nums[stk[tt]] < nums[i]) {
                nextGreater[stk[tt--]] = i;
            }
            stk[++tt] = i;
        }

        stk = new int[N];
        tt = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (tt > 0 && nums[stk[tt]] <= nums[i]) {
                prevGreater[stk[tt--]] = i;
            }
            stk[++tt] = i;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= left && nums[i] <= right) {
                int l = prevGreater[i], r = nextGreater[i];
                res += (i - l) * (r - i);
            }
        }
        return (int) res;
    }

    // S3: Two Pointers
    // time = O(n), space = O(1)
    public int numSubarrayBoundedMax3(int[] nums, int left, int right) {
        int n = nums.length, start = -1, j = -1, res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > right) {
                start = -1;
                j = -1;
            } else {
                if (start == -1) start = i; // 第一个出现 nums[i] <= right的时候，更新start
                if (nums[i] >= left && nums[i] <= right) { // j
                    j = i; // 从 j 之后的所有元素都可以做为右端点,离i越近越好，越晚更新越好，区间长度越大
                }
                if (j != -1) res += j - start + 1;
            }
        }
        return res;
    }

    // S4: two pointers
    // time = O(n), space = O(n)
    public int numSubarrayBoundedMax4(int[] nums, int left, int right) {
        return (int)(calc(nums, right) - calc(nums, left - 1));
    }

    private long calc(int[] nums, int k) {
        long res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > k) continue;
            int j = i + 1;
            while (j < nums.length && nums[j] <= k) j++;
            int t = j - i;
            res += (long)(t + 1) * t / 2;
            i = j;
        }
        return res;
    }
}
/**
 * 数区间：naive O(n^2)
 * 固定一个端点，看另一个端点是否能够快速的确定下来，最常见的就是枚举右端点
 * 1. enumerate Right => Left ?  use hash + prefix sum
 * presum[right] - presum[left] < threshold => presum[right] - threshold < presum[left] => B.S.
 * 2. enumerate the maximum => left? right?
 *              x 9 8 [x x x 8 x x 6] 10
 *                  ^      ^          ^
 * prevGreaterOrEqual[i]   i   k = nextGreater[i]    => monotonic stack
 * 3 pass
 *    4 * 4 = 16 => res = (i - j) * (k - i)
 * ref: LC907
 * [7 {5 5 5 5} 7] [L, R] = [4, 6]
 *  ^  ^        ^
 *  约定：如果有若干个相同的最大值，我们只取第一个。
 *  数subarray的时候永远找它的一个特征值 -> 最大值
 *
 *  S2: O(n)
 *  enumerate right => left?
 *  x right [x x 8 x 3] x [x x x] x  => 有一个元素大于left就OK了, maximum不会超过right
 *           ^   ^   ^
 *           s   j   i
 *  左边界最多只能到right
 */