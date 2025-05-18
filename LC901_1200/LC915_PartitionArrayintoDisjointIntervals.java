package LC901_1200;
import java.util.*;
public class LC915_PartitionArrayintoDisjointIntervals {
    /**
     * Given an array nums, partition it into two (contiguous) subarrays left and right so that:
     *
     * Every element in left is less than or equal to every element in right.
     * left and right are non-empty.
     * left has the smallest possible size.
     * Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
     *
     * Input: nums = [5,0,3,8,6]
     * Output: 3
     *
     * Note:
     *
     * 2 <= nums.length <= 30000
     * 0 <= nums[i] <= 10^6
     * It is guaranteed there is at least one way to partition nums as described.
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int[] r = new int[n];
        Arrays.fill(r, nums[n - 1]);
        for (int i = n - 2; i >= 0; i--) r[i] = Math.min(r[i + 1], nums[i]);

        for (int i = 0, l = Integer.MIN_VALUE; i < n; i++) {
            l = Math.max(l, nums[i]);
            if (l <= r[i + 1]) return i + 1;
        }
        return -1;
    }
}
/**
 * ri: r ~ n-1 中的最小值
 * 0 ~ i的最大值
 */