package LC3301_3600;
import java.util.*;
public class LC3595_OnceTwice {
    /**
     * You are given an integer array nums. In this array:
     *
     * Exactly one element appears once.
     *
     * Exactly one element appears twice.
     *
     * All other elements appear exactly three times.
     *
     * Return an integer array of length 2, where the first element is the one that appears once, and the second is the
     * one that appears twice.
     *
     * Your solution must run in O(n) time and O(1) space.
     *
     * Input: nums = [2,2,3,2,5,5,5,7,7]
     * Output: [3,7]
     *
     * Input: nums = [4,4,6,4,9,9,9,6,8]
     * Output: [8,6]
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^5
     * -2^31 <= nums[i] <= 2^31 - 1
     * nums.length is a multiple of 3.
     * Exactly one element appears once, one element appears twice, and all other elements appear three times.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int[] onceTwice(int[] nums) {
        int[] cnt = new int[32];
        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                if ((x >> i & 1) == 1) cnt[i]++;
            }
        }

        int m = 0, p = 0;
        for (int i = 0; i < 32; i++) {
            int x = cnt[i] % 3;
            if (x != 0) {
                m = x;
                p = i;
                break;
            }
        }
        int t = m == 1 ? 1 : 0;
        int[] res = new int[2];
        Arrays.fill(cnt, 0);
        for (int x : nums) {
            if ((x >> p & 1) == t) {
                for (int i = 0; i < 32; i++) {
                    if ((x >> i & 1) == 1) cnt[i]++;
                }
            }
        }
        for (int i = 0; i < 32; i++) {
            if (cnt[i] % 3 == 1) res[0] |= 1 << i;
        }
        Arrays.fill(cnt, 0);
        for (int x : nums) {
            if (x == res[0]) continue;
            for (int i = 0; i < 32; i++) {
                if ((x >> i & 1) == 1) cnt[i]++;
            }
        }
        for (int i = 0; i < 32; i++) {
            if (cnt[i] % 3 == 2) res[1] |= 1 << i;
        }
        return res;
    }
}