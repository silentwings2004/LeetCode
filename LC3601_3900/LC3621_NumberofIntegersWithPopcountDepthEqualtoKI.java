package LC3601_3900;
import java.util.*;
public class LC3621_NumberofIntegersWithPopcountDepthEqualtoKI {
    /**
     * You are given two integers n and k.
     *
     * For any positive integer x, define the following sequence:
     *
     * p0 = x
     * pi+1 = popcount(pi) for all i >= 0, where popcount(y) is the number of set bits (1's) in the binary
     * representation of y.
     * This sequence will eventually reach the value 1.
     *
     * The popcount-depth of x is defined as the smallest integer d >= 0 such that pd = 1.
     *
     * For example, if x = 7 (binary representation "111"). Then, the sequence is: 7 → 3 → 2 → 1, so the popcount-depth
     * of 7 is 3.
     *
     * Your task is to determine the number of integers in the range [1, n] whose popcount-depth is exactly equal to k.
     *
     * Return the number of such integers.
     *
     * Input: n = 4, k = 1
     * Output: 2
     *
     * Input: n = 7, k = 2
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n <= 10^15
     * 0 <= k <= 5
     * @param n
     * @param k
     * @return
     */
    // time = O(logn * logn), space = O(logn)
    public long popcountDepth(long n, int k) {
        if (k == 0) return 1;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= 60; i++) {
            if (get(i) == k - 1) set.add(i);
        }
        String s = Long.toBinaryString(n);
        while (s.length() < 60) s = "0" + s;

        HashMap<String, Long> f = new HashMap<>();
        long res = dp(0, 0, true, false, s, set, f);
        if (k == 1) res--;
        return res;
    }

    private long dp(int u, int cnt, boolean isLimit, boolean isNum, String s, HashSet<Integer> set, HashMap<String, Long> f) {
        if (u == s.length()) return isNum && set.contains(cnt) ? 1 : 0;
        String h = u + "#" + cnt + "#" + (isLimit ? 1 : 0) + "#" + (isNum ? 1 : 0);
        if (f.containsKey(h)) return f.get(h);

        long res = 0;
        int up = isLimit ? s.charAt(u) - '0' : 1;
        for (int i = 0; i <= up; i++) {
            res += dp(u + 1, cnt + i, isLimit && i == up, i > 0 ? true : isNum, s, set, f);
        }
        f.put(h, res);
        return res;
    }

    private int get(int x) {
        int d = 0;
        while (x != 1) {
            x = Integer.bitCount(x);
            d++;
        }
        return d;
    }
}