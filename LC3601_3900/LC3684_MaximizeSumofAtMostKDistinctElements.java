package LC3601_3900;
import java.util.*;
public class LC3684_MaximizeSumofAtMostKDistinctElements {
    /**
     * You are given a positive integer array nums and an integer k.
     *
     * Choose at most k elements from nums so that their sum is maximized. However, the chosen numbers must be distinct.
     *
     * Return an array containing the chosen numbers in strictly descending order.
     *
     * Input: nums = [84,93,100,77,90], k = 3
     * Output: [100,93,90]
     *
     * Input: nums = [84,93,100,77,93], k = 3
     * Output: [100,93,84]
     *
     * Input: nums = [1,1,1,2,2,2], k = 6
     * Output: [2,1]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(k)
    public int[] maxKDistinct(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        List<Integer> q = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) q.add(nums[i]);
            else if (nums[i] != q.get(q.size() - 1)) q.add(nums[i]);
            if (q.size() == k) break;
        }
        int[] res = new int[q.size()];
        for (int i = 0; i < q.size(); i++) res[i] = q.get(i);
        return res;
    }
}