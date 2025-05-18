package LC2401_2700;
import java.util.*;
public class LC2448_MinimumCosttoMakeArrayEqual {
    /**
     * You are given two 0-indexed arrays nums and cost consisting each of n positive integers.
     *
     * You can do the following operation any number of times:
     *
     * Increase or decrease any element of the array nums by 1.
     * The cost of doing one operation on the ith element is cost[i].
     *
     * Return the minimum total cost such that all the elements of the array nums become equal.
     *
     * Input: nums = [1,3,5,2], cost = [2,3,1,14]
     * Output: 8
     *
     * Input: nums = [2,2,2,2,2], cost = [4,2,8,1,3]
     * Output: 0
     *
     * Constraints:
     *
     * n == nums.length == cost.length
     * 1 <= n <= 10^5
     * 1 <= nums[i], cost[i] <= 10^6
     * @param nums
     * @param cost
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{nums[i], cost[i]};
        }
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        long[] left = new long[n];
        long presumCost = arr[0][1];
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] + presumCost * (arr[i][0] - arr[i - 1][0]);
            presumCost += arr[i][1];
        }

        long[] right = new long[n];
        presumCost = arr[n - 1][1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] + presumCost * (arr[i + 1][0] - arr[i][0]);
            presumCost += arr[i][1];
        }

        long[] all = new long[n];
        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            all[i] = left[i] + right[i];
            res = Math.min(res, all[i]);
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public long minCost2(int[] nums, int[] cost) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        long totalCost = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{nums[i], cost[i]};
            totalCost += cost[i];
        }
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        long count = 0;
        int k = 0;
        for (int i = 0; i < n; i++) {
            count += arr[i][1];
            if (count >= totalCost * 1.0 / 2) {
                k = i;
                break;
            }
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            res += (long) Math.abs(arr[i][0] - arr[k][0]) * arr[i][1];
        }
        return res;
    }
}
/**
 * 三分搜索
 * best equal value一定是数组里的元素
 * ... i-2, i-1, nums[i] x x+1 nums[j] j+1, j+2,...
 * delta = costSum[0:i] * 1 - costSum[j:n-1] * 1
 * delta = -costSum[0:i] * 1 + costSum[j:n-1] * 1
 * costLeft, costRight
 * costLeft[i] = costLeft[i-1] + costSum[0:i-1] * (nums[i] - nums[i-1])
 * costRight[i]
 * min(costLeft[i]+costRight[i])
 *
 * S2:
 * min(abs(xi -P)) -> median 找中位数
 * total_cost / 2
 */