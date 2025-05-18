package LC2700_3000;
import java.util.*;
public class LC2856_MinimumArrayLengthAfterPairRemovals {
    /**
     * You are given a 0-indexed sorted array of integers nums.
     *
     * You can perform the following operation any number of times:
     *
     * Choose two indices, i and j, where i < j, such that nums[i] < nums[j].
     * Then, remove the elements at indices i and j from nums. The remaining elements retain their original order, and
     * the array is re-indexed.
     * Return an integer that denotes the minimum length of nums after performing the operation any number of times
     * (including zero).
     *
     * Note that nums is sorted in non-decreasing order.
     *
     * Input: nums = [1,3,4,9]
     * Output: 0
     *
     * Input: nums = [2,3,6,9]
     * Output: 0
     *
     * Input: nums = [1,1,2]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * nums is sorted in non-decreasing order.
     * @param nums
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int minLengthAfterRemovals(List<Integer> nums) {
        int n = nums.size();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums.get(j).equals(nums.get(j - 1))) j++;
            int len = j - i;
            pq.offer(new int[]{len, nums.get(i)});
            i = j - 1;
        }

        while (pq.size() > 1) {
            int[] x = pq.poll(), y = pq.poll();
            x[0]--;
            y[0]--;
            if (x[0] > 0) pq.offer(x);
            if (y[0] > 0) pq.offer(y);
        }
        return pq.isEmpty() ? 0 : pq.peek()[0];
    }

    // S2: binary search
    // time = O(logn), space = O(1)
    public int minLengthAfterRemovals2(List<Integer> nums) {
        int n = nums.size();
        int x = nums.get(n / 2);
        int maxCnt = lowerBound(nums, x + 1) - lowerBound(nums, x);
        return Math.max(maxCnt * 2 - n, n % 2);
    }

    private int lowerBound(List<Integer> nums, int t) {
        int l = 0, r = nums.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums.get(mid) >= t) r = mid;
            else l = mid + 1;
        }
        return nums.get(r) >= t ? r : r + 1;
    }

    // S3: sliding window
    // time = O(n), space = O(1)
    public int minLengthAfterRemovals3(List<Integer> nums) {
        int n = nums.size(), res = n;
        for (int i = 0, j = n / 2; i < n / 2 && j < n; j++) {
            if (nums.get(i) < nums.get(j)) {
                i++;
                res -= 2;
            }
        }
        return res;
    }
}
/**
 * 分类讨论：
 * 1. cx > n / 2
 * 没法全部抵消掉 => 2 * cx - n
 * 2. cx <= n / 2
 * 可以几乎全部抵消
 * n 是偶数，剩余0个数
 * n 是奇数, 剩余1个数
 */
