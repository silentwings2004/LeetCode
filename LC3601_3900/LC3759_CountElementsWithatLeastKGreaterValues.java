package LC3601_3900;
import java.util.*;
public class LC3759_CountElementsWithatLeastKGreaterValues {
    /**
     * You are given an integer array nums of length n and an integer k.
     *
     * An element in nums is said to be qualified if there exist at least k elements in the array that are strictly
     * greater than it.
     *
     * Return an integer denoting the total number of qualified elements in nums.
     *
     * Input: nums = [3,1,2], k = 1
     * Output: 2
     *
     * Input: nums = [5,5,5], k = 2
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 0 <= k < n
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int countElements(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, res = 0;
        for (int i = n - 1; i >= 0; i--) {
            int j = i - 1;
            while (j >= 0 && nums[j] == nums[j + 1]) j--;
            int len = i - j;
            if (n - 1 - i >= k) res += len;
            i = j + 1;
        }
        return res;
    }
}