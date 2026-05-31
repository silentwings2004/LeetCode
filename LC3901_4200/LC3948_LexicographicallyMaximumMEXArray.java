package LC3901_4200;
import java.util.*;
public class LC3948_LexicographicallyMaximumMEXArray {
    /**
     * You are given an integer array nums.
     *
     * You want to construct an array result by repeatedly performing the following operation until nums becomes empty:
     *
     * Choose an integer k such that 1 <= k <= len(nums).
     * Compute the MEX of the first k elements of nums.
     * Append this MEX to result.
     * Remove the first k elements from nums.
     *
     * Return the lexicographically maximum array result that can be obtained after performing the operations.
     *
     * The MEX of an array is the smallest non-negative integer not present in the array.
     *
     * An array a is lexicographically greater than an array b if in the first position where a and b differ, array a
     * has an element that is greater than the corresponding element in b. If the first min(a.length, b.length)
     * elements do not differ, then the longer array is the lexicographically greater one.
     *
     * Input: nums = [0,1,0]
     * Output: [2,1]
     *
     * Input: nums = [1,0,2]
     * Output: [3]
     *
     * Input: nums = [3,1]
     * Output: [0,0]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int[] maximumMEX(int[] nums) {
        int n = nums.length;
        Deque<Integer>[] pos = new Deque[n + 1];
        for (int i = 0; i <= n; i++) pos[i] = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] < n) pos[nums[i]].offerLast(i);
        }

        int idx = 0;
        for (int i = 0; i < n; i++) {
            int start = i, mex = 0;
            while (true) {
                while (pos[mex].size() > 0 && pos[mex].peekFirst() < start) pos[mex].pollFirst();
                if (pos[mex].isEmpty()) break;
                i = Math.max(i, pos[mex].peekFirst());
                mex++;
            }
            nums[idx++] = mex;
        }
        return Arrays.copyOf(nums, idx);
    }
}