package LC3301_3600;
import java.util.*;
public class LC3337_TotalCharactersinStringAfterTransformationsII {
    /**
     * You are given a string s consisting of lowercase English letters, an integer t representing the number of
     * transformations to perform, and an array nums of size 26. In one transformation, every character in s is replaced
     * according to the following rules:
     *
     * Replace s[i] with the next nums[s[i] - 'a'] consecutive characters in the alphabet. For example, if s[i] = 'a'
     * and nums[0] = 3, the character 'a' transforms into the next 3 consecutive characters ahead of it, which results
     * in "bcd".
     * The transformation wraps around the alphabet if it exceeds 'z'. For example, if s[i] = 'y' and nums[24] = 3, the
     * character 'y' transforms into the next 3 consecutive characters ahead of it, which results in "zab".
     * Return the length of the resulting string after exactly t transformations.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: s = "abcyy", t = 2, nums = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2]
     * Output: 7
     *
     * Input: s = "azbk", t = 1, nums = [2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2]
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists only of lowercase English letters.
     * 1 <= t <= 10^9
     * nums.length == 26
     * 1 <= nums[i] <= 25
     * @param s
     * @param t
     * @param nums
     * @return
     */
    // time = O(n + 26^3 * logt), space = O(26^2)
    long mod = (long)(1e9 + 7);
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) cnt[s.charAt(i) - 'a']++;

        long[][] f = new long[26][26];
        for (int i = 0; i < 26; i++) {
            int x = nums.get(i);
            for (int j = i + 1; j <= i + x; j++) {
                f[i][j % 26] = 1;
            }
        }
        f = qmi(f, t);

        long res = 0;
        for (int i = 0; i < 26; i++) {
            long v = 0;
            for (long x : f[i]) v = (v + x) % mod;
            res = (res + v * cnt[i]) % mod;
        }
        return (int)res;
    }

    private long[][] qmi(long[][] f, int k) {
        long[][] res = new long[26][26];
        for (int i = 0; i < 26; i++) res[i][i] = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = mul(res, f);
            f = mul(f, f);
            k >>= 1;
        }
        return res;
    }

    private long[][] mul(long[][] a, long[][] b) {
        long[][] c = new long[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < 26; k++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }
        return c;
    }
}