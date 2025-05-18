package LC3301_3600;
import java.util.*;
public class LC3416_SubsequenceswithaUniqueMiddleModeII {
    /**
     * Given an integer array nums, find the number of
     * subsequences of size 5 of nums with a unique middle mode.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * A mode of a sequence of numbers is defined as the element that appears the maximum number of times in the sequence.
     *
     * A sequence of numbers contains a unique mode if it has only one mode.
     *
     * A sequence of numbers seq of size 5 contains a unique middle mode if the middle element (seq[2]) is a unique mode.
     *
     * Input: nums = [1,1,1,1,1,1]
     * Output: 6
     *
     * Input: nums = [1,2,2,3,3,4]
     * Output: 4
     *
     * Input: nums = [0,1,2,3,4,5,6,7,8]
     * Output: 0
     *
     * Constraints:
     *
     * 5 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    final long mod = (long)(1e9 + 7);
    public int subsequencesWithMiddleMode(int[] nums) {
        int n = nums.length;
        long res = 1L * n * (n - 1) % mod * (n - 2) % mod * (n - 3) % mod * (n - 4) % mod * inv(120) % mod;
        HashMap<Integer, Integer> suf = new HashMap<>();
        for (int x : nums) suf.put(x, suf.getOrDefault(x, 0) + 1);
        if (suf.size() == 1) return (int)res;

        HashMap<Integer, Integer> pre = new HashMap<>();
        long cp = 0, cs = 0, ps = 0, p2s = 0, ps2 = 0;

        for (int v : suf.values()) {
            cs = (cs + comb(v)) % mod;
        }

        for (int i = 0; i < n - 2; i++) {
            int x = nums[i];
            suf.put(x, suf.get(x) - 1);

            int px = pre.getOrDefault(x, 0);
            int sx = suf.get(x);

            cs = (cs - sx + mod) % mod;
            ps = (ps - px + mod) % mod;
            p2s = (p2s - px * px % mod + mod) % mod;
            ps2 = (ps2 - (sx * 2L + 1) % mod * px % mod + mod) % mod;

            int j = n - 1 - i;

            res = (res - comb(i - px) * comb(j - sx) % mod + mod) % mod;
            res = (res - (cp - comb(px)) * sx % mod * (j - sx) % mod + mod) % mod;
            res = (res - (cs - comb(sx)) * px % mod * (i - px) % mod + mod) % mod;
            res = (res - ((ps - px * sx % mod) * (j - sx) % mod - (ps2 - px * sx % mod * sx % mod + mod) % mod + mod) % mod * px % mod + mod) % mod;
            res = (res - ((ps - px * sx % mod) * (i - px) % mod - (p2s - px * px % mod * sx % mod + mod) % mod + mod) % mod * sx % mod + mod) % mod;

            cp = (cp + px) % mod;
            ps = (ps + sx) % mod;
            ps2 = (ps2 + sx * sx % mod) % mod;
            p2s = (p2s + (px * 2L + 1) % mod * sx % mod) % mod;

            pre.put(x, pre.getOrDefault(x, 0) + 1);
        }

        return (int) res;

    }

    private long comb(int x) {
        if (x < 2) return 0;
        return 1L * x * (x - 1) % mod * inv(2) % mod;
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }

    private long inv(int a) {
        return qmi(a, mod - 2);
    }
}