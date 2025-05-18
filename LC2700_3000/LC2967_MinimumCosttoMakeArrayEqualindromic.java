package LC2700_3000;
import java.util.*;
public class LC2967_MinimumCosttoMakeArrayEqualindromic {
    /**
     * You are given a 0-indexed integer array nums having length n.
     *
     * You are allowed to perform a special move any number of times (including zero) on nums. In one special move you
     * perform the following steps in order:
     *
     * Choose an index i in the range [0, n - 1], and a positive integer x.
     * Add |nums[i] - x| to the total cost.
     * Change the value of nums[i] to x.
     * A palindromic number is a positive integer that remains the same when its digits are reversed. For example, 121,
     * 2552 and 65756 are palindromic numbers whereas 24, 46, 235 are not palindromic numbers.
     *
     * An array is considered equalindromic if all the elements in the array are equal to an integer y, where y is a
     * palindromic number less than 109.
     *
     * Return an integer denoting the minimum possible total cost to make nums equalindromic by performing any number
     * of special moves.
     *
     * Input: nums = [1,2,3,4,5]
     * Output: 6
     *
     * Input: nums = [10,12,13,14,15]
     * Output: 11
     *
     * Input: nums = [22,33,22,33,22]
     * Output: 22
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(logn)
    int[] nums;
    public long minimumCost(int[] nums) {
        this.nums = nums;
        Arrays.sort(nums);
        int n = nums.length;
        int mid = n % 2 == 1 ? nums[n / 2] : (nums[n / 2 - 1] + nums[n / 2]) / 2;
        long a = 0, b = 0;
        for (long i = mid;; i++) {
            if (check(i)) {
                a = i;
                break;
            }
        }
        for (long i = mid;; i--) {
            if (check(i)) {
                b = i;
                break;
            }
        }
        return Math.min(helper(a), helper(b));
    }

    private boolean check(long x) {
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        return sb.toString().equals(sb.reverse().toString());
    }

    private long helper(long t) {
        long res = 0;
        for (int x: nums) res += Math.abs(x - t);
        return res;
    }
}
/**
 * 中位数贪心
 * 10 20 30 40 => 差的绝对值之和最小
 * 取中位数还是平均值？
 * 1 1 2 3 99
 *     ^
 * 如果取平均值 = 106 / 5 > 20
 * 给定一个数组 a,那么取 a 的中位数 x
 * x 到 a 中所有数的距离之和是最小的
 */