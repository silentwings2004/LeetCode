package LC2101_2400;
import java.util.*;
public class LC2341_MaximumNumberofPairsinArray {
    /**
     * You are given a 0-indexed integer array nums. In one operation, you may do the following:
     *
     * Choose two integers in nums that are equal.
     * Remove both integers from nums, forming a pair.
     * The operation is done on nums as many times as possible.
     *
     * Return a 0-indexed integer array answer of size 2 where answer[0] is the number of pairs that are formed and
     * answer[1] is the number of leftover integers in nums after doing the operation as many times as possible.
     *
     * Input: nums = [1,3,2,1,3,2,2]
     * Output: [3,1]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 110;
    public int[] numberOfPairs(int[] nums) {
        int[] cnt = new int[N];
        for (int x : nums) cnt[x]++;
        int[] res = new int[2];
        for (int i = 0; i <= 100; i++) {
            if (cnt[i] >= 2) {
                res[0] += cnt[i] / 2;
                if (cnt[i] % 2 != 0) res[1]++;
            } else if (cnt[i] == 1) res[1]++;
        }
        return res;
    }
}
