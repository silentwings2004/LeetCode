package LC1201_1500;
import java.util.*;
public class LC1403_MinimumSubsequenceinNonIncreasingOrder {
    /**
     * Given the array nums, obtain a subsequence of the array whose sum of elements is strictly greater than the sum
     * of the non included elements in such subsequence.
     *
     * If there are multiple solutions, return the subsequence with minimum size and if there still exist multiple
     * solutions, return the subsequence with the maximum total sum of all its elements. A subsequence of an array can
     * be obtained by erasing some (possibly zero) elements from the array.
     *
     * Note that the solution with the given constraints is guaranteed to be unique. Also return the answer sorted in
     * non-increasing order.
     *
     * Input: nums = [4,3,10,9,8]
     * Output: [10,9]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 500
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public List<Integer> minSubsequence(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int[] cnt = new int[101];
        int total = 0;
        for (int x : nums) {
            cnt[x]++;
            total += x;
        }

        int sum = 0;
        boolean flag = false;
        for (int i = 100; i >= 0; i--) {
            while (cnt[i] > 0) {
                res.add(i);
                sum += i;
                cnt[i]--;
                if (sum > total - sum) {
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        return res;
    }
}
