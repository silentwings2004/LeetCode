package LC3301_3600;
import java.util.*;
public class LC3507_MinimumPairRemovaltoSortArrayI {
    /**
     * Given an array nums, you can perform the following operation any number of times:
     *
     * Select the adjacent pair with the minimum sum in nums. If multiple such pairs exist, choose the leftmost one.
     * Replace the pair with their sum.
     * Return the minimum number of operations needed to make the array non-decreasing.
     *
     * An array is said to be non-decreasing if each element is greater than or equal to its previous element (if it
     * exists).
     *
     * Input: nums = [5,2,3,1]
     * Output: 2
     *
     * Input: nums = [1,2,2]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50
     * -1000 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(n)
    public int minimumPairRemoval(int[] nums) {
        int res = 0;
        while (true) {
            int n = nums.length;
            if (check(nums)) break;
            res++;
            int ms = Integer.MAX_VALUE, idx = -1;
            for (int i = 1; i < n; i++) {
                int t = nums[i] + nums[i - 1];
                if (t < ms) {
                    ms = t;
                    idx = i - 1;
                }
            }
            List<Integer> q = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (i != idx) q.add(nums[i]);
                else {
                    q.add(nums[i] + nums[i + 1]);
                    i++;
                }
            }
            nums = new int[n - 1];
            for (int i = 0; i < n - 1; i++) nums[i] = q.get(i);
        }
        return res;
    }

    private boolean check(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            if (a[i] < a[i - 1]) return false;
        }
        return true;
    }
}