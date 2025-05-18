package LC601_900;
import java.util.*;
public class LC689_MaximumSumof3NonOverlappingSubarrays {
    /**
     * Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum
     * and return them.
     *
     * Return the result as a list of indices representing the starting position of each interval (0-indexed). If there
     * are multiple answers, return the lexicographically smallest one.
     *
     * Input: nums = [1,2,1,2,6,7,5,1], k = 2
     * Output: [0,3,5]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2 * 10^4
     * 1 <= nums[i] < 2^16
     * 1 <= k <= floor(nums.length / 3)
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0 || k <= 0) return new int[0];

        int n = nums.length;

        //从左边遍历
        int[] range = new int[n]; // /以i位置结尾的子数组之和
        int[] left = new int[n]; // 位置i及之前：sum最大的子数组的末尾元素的idx

        int sum = 0;
        for (int i = 0; i < k; i++) sum += nums[i];
        range[k - 1] = sum;
        left[k - 1] = k - 1;

        int max = sum;
        for (int i = k; i < n; i++) { // 从左边遍历数组
            sum = sum - nums[i - k] + nums[i]; // 子数组之和
            range[i] = sum; // 存放到range数组

            // 处理left
            left[i] = left[i - 1]; // 假设和最大的数组没变
            if (sum > max) { // 当前sum比max大，更新max, 用大于是为了保证字典序
                max = sum;
                left[i] = i;
            }
        }

        // 从右边遍历
        sum = 0;
        for (int i = n - 1; i >= n - k; i--) sum += nums[i];
        max = sum;
        int[] right = new int[n]; // i及i之后：sum最大的子数组起始元素的下标idx
        right[n - k] = n - k;
        for (int i = n - k - 1; i >= 0; i--) {
            sum = sum - nums[i + k] + nums[i];
            right[i] = right[i + 1];
            if (sum >= max) { // 大于等于号保证字典序
                max = sum;
                right[i] = i;
            }
        }

        int a = 0, b = 0, c = 0;
        max = 0;
        for (int i = 2 * k - 1; i < n - k; i++) { // 中间一块的起始点 (0...k-1)选不了 i == n-1
            int p1 = range[left[i - k]]; // 左边的子数组最大和
            int p2 = range[i]; // 中间数组之和
            int p3 = range[right[i + 1] + k - 1]; // right[i+1]表示第三个子数组（和最大）的开始元素坐标
            if (p1 + p2 + p3 > max) {
                max = p1 + p2 + p3;
                a = left[i - k] - (k - 1);
                b = i - (k - 1);
                c = right[i + 1];
            }
        }
        return new int[]{a, b, c};
    }

    // S2:
    // time = O(n), space = O(n)
    public int[] maxSumOfThreeSubarrays2(int[] nums, int k) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        int[][] f = new int[n + 2][4];

        int x = n + 1, y = 3;
        for (int i = n - k + 1; i > 0; i--) {
            for (int j = 1; j <= 3; j++) {
                f[i][j] = Math.max(f[i + 1][j], f[i + k][j - 1] + s[i + k - 1] - s[i - 1]);
            }
            if (f[x][3] <= f[i][3]) x = i;
        }

        int[] res = new int[3];
        int idx = 0;
        while (y > 0) {
            while (f[x][y] != f[x + k][y - 1] + s[x + k - 1] - s[x - 1]) x++;
            res[idx++] = x - 1;
            x += k;
            y--;
        }
        return res;
    }
}
/**
 * f(i,j): 前i个数中找到j个不重叠子数组的最大和
 * 不用ai: f(i-1,j)
 * 用ai: f(i-k,j-1)+Si-Sk
 * f(i,j) = max{f(i-1,j), f(i-k,j-1) + Si-Sk}
 * 倒序dp求方案
 */