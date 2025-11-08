package LC3601_3900;
import java.util.*;
public class LC3731_FindMissingElements {
    /**
     * You are given an integer array nums consisting of unique integers.
     *
     * Originally, nums contained every integer within a certain range. However, some integers might have gone missing
     * from the array.
     *
     * The smallest and largest integers of the original range are still present in nums.
     *
     * Return a sorted list of all the missing integers in this range. If no integers are missing, return an empty list.
     *
     * Input: nums = [1,4,2,5]
     * Output: [3]
     *
     * Input: nums = [7,8,6,9]
     * Output: []
     *
     * Input: nums = [5,1]
     * Output: [2,3,4]
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public List<Integer> findMissingElements(int[] nums) {
        boolean[] st = new boolean[101];
        int l = 101, r = 0;
        for (int x : nums) {
            st[x] = true;
            l = Math.min(l, x);
            r = Math.max(r, x);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = l + 1; i < r; i++) {
            if (st[i]) continue;
            res.add(i);
        }
        return res;
    }
}