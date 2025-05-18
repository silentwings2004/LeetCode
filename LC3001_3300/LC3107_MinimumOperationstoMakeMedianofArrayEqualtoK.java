package LC3001_3300;
import java.util.*;
public class LC3107_MinimumOperationstoMakeMedianofArrayEqualtoK {
    /**
     * You are given an integer array nums and a non-negative integer k. In one operation, you can increase or decrease
     * any element by 1.
     *
     * Return the minimum number of operations needed to make the median of nums equal to k.
     *
     * Input: nums = [2,5,6,8,5], k = 4
     * Output: 2
     *
     * Input: nums = [2,5,6,8,5], k = 7
     * Output: 3
     *
     * Input: nums = [1,2,3,4,5,6], k = 4
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2 * 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // S1: heap
    // time = O(nlogn), space = O(n)
    PriorityQueue<Integer> minh, maxh;
    int n;
    public long minOperationsToMakeMedianK(int[] nums, int k) {
        minh = new PriorityQueue<>();
        maxh = new PriorityQueue<>((o1, o2) -> o2 - o1);
        n = nums.length;
        for (int x : nums) add(x);

        long res = 0;
        while (get() != k) {
            res += Math.abs(k - get());
            if (n % 2 == 1) {
                minh.poll();
                add(k);
            } else {
                if (minh.peek() == get()) minh.poll();
                else maxh.poll();
                add(k);
            }
        }
        return res;
    }

    private void add(int x) {
        maxh.offer(x);
        minh.offer(maxh.poll());
        if (minh.size() - maxh.size() > 1) {
            maxh.offer(minh.poll());
        }
    }

    private int get() {
        if (n % 2 == 1) return minh.peek();
        return Math.max(minh.peek(), maxh.peek());
    }

    // S2
    // time = O(nlogn), space = O(logn)
    public long minOperationsToMakeMedianK2(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, idx = n / 2;
        long res = 0;
        if (nums[idx] > k) {
            for (int i = idx; i >= 0 && nums[i] > k; i--) {
                res += nums[i] - k;
            }
        } else {
            for (int i = idx; i < n && nums[i] < k; i++) {
                res += k - nums[i];
            }
        }
        return res;
    }
}