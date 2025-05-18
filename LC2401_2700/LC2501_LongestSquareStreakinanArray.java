package LC2401_2700;
import java.util.*;
public class LC2501_LongestSquareStreakinanArray {
    /**
     * You are given an integer array nums. A subsequence of nums is called a square streak if:
     *
     * The length of the subsequence is at least 2, and
     * after sorting the subsequence, each element (except the first element) is the square of the previous number.
     * Return the length of the longest square streak in nums, or return -1 if there is no square streak.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without
     * changing the order of the remaining elements.
     *
     * Input: nums = [4,3,6,16,8,2]
     * Output: 3
     *
     * Input: nums = [2,3,5,6,7]
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 2 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // S1: LIS
    // time = O(nlogn), space = O(n)
    public int longestSquareStreak(int[] nums) {
        Arrays.sort(nums);
        int[] f = new int[100010];
        int res = 0;
        for (int x : nums) {
            int y = (int)Math.sqrt(x);
            f[x] = 1 + (y * y == x ? f[y] : 0);
            res = Math.max(res, f[x]);
        }
        return res < 2 ? -1 : res;
    }

    // S2: sort + TreeMap
    // time = O(nlogn), space = O(n)
    public int longestSquareStreak2(int[] nums) {
        Arrays.sort(nums);
        TreeSet<Long> set = new TreeSet<>();
        for (int x : nums) set.add((long) x);

        int res = 0;
        for (int x : nums) {
            if (!set.contains((long) x)) continue;
            long y = (long) x * x;
            int cnt = 1;
            while (set.contains(y)) {
                cnt++;
                set.remove(y);
                y *= y;
            }
            res = Math.max(res, cnt);
        }
        return res == 1 ? -1 : res;
    }
}
/**
 * f(ai) = f(sqrt(a)) + 1
 */