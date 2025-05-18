package LC3301_3600;

public class LC3334_FindtheMaximumFactorScoreofArray {
    /**
     * You are given an integer array nums.
     *
     * The factor score of an array is defined as the product of the LCM and GCD of all elements of that array.
     *
     * Return the maximum factor score of nums after removing at most one element from it.
     *
     * Note that both the LCM and GCD of a single number are the number itself, and the factor score of an empty array
     * is 0.
     *
     * The term lcm(a, b) denotes the least common multiple of a and b.
     *
     * The term gcd(a, b) denotes the greatest common divisor of a and b.
     *
     * Input: nums = [2,4,8,16]
     * Output: 64
     *
     * Input: nums = [1,2,3,4,5]
     * Output: 60
     *
     * Input: nums = [3]
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 30
     * @param nums
     * @return
     */
    // S1: brute-force
    // time = O(n^2), space = O(1)
    public long maxScore(int[] nums) {
        int n = nums.length;
        long gd = 0, ld = 1;
        for (int x : nums) {
            gd = gcd(gd, x);
            ld = lcm(ld, x);
        }
        long res = gd * ld;
        for (int i = 0; i < n; i++) {
            gd = 0;
            ld = 1;
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                gd = gcd(gd, nums[j]);
                ld = lcm(ld, nums[j]);
            }
            res = Math.max(res, gd * ld);
        }
        return res;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    // S2: 前后缀分解
    public long maxScore2(int[] nums) {
        int n = nums.length;
        int[] sg = new int[n + 1];
        long[] sl = new long[n + 1];
        sl[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            sg[i] = (int)gcd(sg[i + 1], nums[i]);
            sl[i] = lcm(sl[i + 1], nums[i]);
        }

        long res = sg[0] * sl[0];
        int pg = 0;
        long pl = 1;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, gcd(pg, sg[i + 1]) * lcm(pl, sl[i + 1]));
            pg = (int)gcd(pg, nums[i]);
            pl = lcm(pl, nums[i]);
        }
        return res;
    }
}
/**
 * 参考LC238
 * 前后缀分解
 * gcd 初始化为0，lcm初始化为1
 * 根据gcd的定义, gcd(0,x) = ?
 */