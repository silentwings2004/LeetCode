package LC3601_3900;
import java.util.*;
public class LC3819_RotateNonNegativeElements {
    /**
     * You are given an integer array nums and an integer k.
     *
     * Rotate only the non-negative elements of the array to the left by k positions, in a cyclic manner.
     *
     * All negative elements must stay in their original positions and must not move.
     *
     * After rotation, place the non-negative elements back into the array in the new order, filling only the positions
     * that originally contained non-negative values and skipping all negative positions.
     *
     * Return the resulting array.
     *
     * Input: nums = [1,-2,3,-4], k = 3
     * Output: [3,-2,1,-4]
     *
     * Input: nums = [-3,-2,7], k = 1
     * Output: [-3,-2,7]
     *
     * Input: nums = [5,4,-9,6], k = 2
     * Output: [6,5,-9,4]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^5 <= nums[i] <= 10^5
     * 0 <= k <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int[] rotateElements(int[] nums, int k) {
        List<int[]> q = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) q.add(new int[]{nums[i], i});
        }
        int m = q.size();
        for (int i = 0; i < m; i++) {
            int v = q.get(i)[0];
            int idx = ((i - k) % m + m) % m;
            nums[q.get(idx)[1]] = v;
        }
        return nums;
    }
}