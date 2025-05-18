package LC3001_3300;

public class LC3226_NumberofBitChangestoMakeTwoIntegersEqual {
    /**
     * You are given two positive integers n and k.
     *
     * You can choose any bit in the binary representation of n that is equal to 1 and change it to 0.
     *
     * Return the number of changes needed to make n equal to k. If it is impossible, return -1.
     *
     * Input: n = 13, k = 4
     *
     * Output: 2
     *
     * Input: n = 21, k = 21
     *
     * Output: 0
     *
     * Input: n = 14, k = 13
     *
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n, k <= 10^6
     * @param n
     * @param k
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public int minChanges(int n, int k) {
        int res = 0;
        for (int i = 20; i >= 0; i--) {
            int a = n >> i & 1, b = k >> i & 1;
            if (a == b) continue;
            if (a == 0 && b == 1) return -1;
            res++;
        }
        return res;
    }

    // S2
    // time = O(1), space = O(1)
    public int minChanges2(int n, int k) {
        return (n & k) != k ? -1 : Integer.bitCount(n ^ k);
    }
}