package LC301_600;
import java.util.*;
public class LC413_ArithmeticSlices {
    /**
     * A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q)
     * such that 0 <= P < Q < N.
     *
     * A slice (P, Q) of the array A is called arithmetic if the sequence:
     * A[P], A[P + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
     *
     * The function should return the number of arithmetic slices in the array A.
     *
     * Example:
     *
     * A = [1, 2, 3, 4]
     *
     * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
     *
     * @param nums
     * @return
     */
    // S1:
    // time = O(n), space = O(n)
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int diff = nums[0] - nums[1], count = 0, res = 0;

        for (int i = 2; i < n; i++) {
            if (nums[i - 1] - nums[i] == diff) count++;
            else {
                diff = nums[i - 1] - nums[i];
                count = 0;
            }
            res += count;
        }
        return res;
    }

    // S2: DP
    // time = O(n), space = O(1)
    public int numberOfArithmeticSlices2(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] dp = new int[n];

        int res = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                res += dp[i];
            }
        }
        return res;
    }

    // S3: diff array
    // time = O(n), space = O(1)
    public int numberOfArithmeticSlices3(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i > 0; i--) nums[i] -= nums[i - 1];
        int res = 0;
        for (int i = 1; i < n; i++) { // 注意：原数组已经改造成了差分数组，所以i得从1开始才有意义!
            int j = i;
            while (j < n && nums[j] == nums[i]) j++;
            int k = j - i;
            res += k * (k - 1) / 2;
            i = j - 1;
        }
        return res;
    }
}
/**
 * 至少包含3个元素
 * 相邻2个元素之差相等
 * 把原数组变成差分数组 => 在差分数组里找连续相等的一段
 * 有多少个长度 >= 3的子区间
 */