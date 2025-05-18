package LC1501_1800;

public class LC1652_DefusetheBomb {
    /**
     * You have a bomb to defuse, and your time is running out! Your informer will provide you with a circular array
     * code of length of n and a key k.
     *
     * To decrypt the code, you must replace every number. All the numbers are replaced simultaneously.
     *
     * If k > 0, replace the ith number with the sum of the next k numbers.
     * If k < 0, replace the ith number with the sum of the previous k numbers.
     * If k == 0, replace the ith number with 0.
     * As code is circular, the next element of code[n-1] is code[0], and the previous element of code[0] is code[n-1].
     *
     * Given the circular array code and an integer key k, return the decrypted code to defuse the bomb!
     *
     * Input: code = [5,7,1,4], k = 3
     * Output: [12,10,16,13]
     *
     * Input: code = [1,2,3,4], k = 0
     * Output: [0,0,0,0]
     *
     * Input: code = [2,4,9,3], k = -2
     * Output: [12,5,6,13]
     *
     * Constraints:
     *
     * n == code.length
     * 1 <= n <= 100
     * 1 <= code[i] <= 100
     * -(n - 1) <= k <= n - 1
     * @param code
     * @param k
     * @return
     */
    // S1
    // time = O(n * k), space = O(1)
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] res = new int[n];
        if (k == 0) return res;
        for (int i = 0; i < n; i++) {
            int t = 0;
            for (int j = 1; j <= Math.abs(k); j++) {
                int idx = k > 0 ? (i + j) % n : (i - j + n) % n;
                t += code[idx];
            }
            res[i] = t;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int[] decrypt2(int[] code, int k) {
        int n = code.length;
        int[] res = new int[n];
        if (k == 0) return res;
        int[] s = new int[n * 2 + 1];
        for (int i = 1; i <= n * 2; i++) s[i] = s[i - 1] + code[(i - 1) % n];
        for (int i = 1; i <= n; i++) {
            if (k < 0) res[i - 1] = s[i - 1 + n] - s[i - 1 + k + n];
            else res[i - 1] = s[i + k] - s[i];
        }
        return res;
    }
}
