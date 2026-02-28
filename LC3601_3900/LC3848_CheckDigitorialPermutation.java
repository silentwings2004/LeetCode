package LC3601_3900;

public class LC3848_CheckDigitorialPermutation {
    /**
     * You are given an integer n.
     *
     * A number is called digitorial if the sum of the factorials of its digits is equal to the number itself.
     *
     * Determine whether any permutation of n (including the original order) forms a digitorial number.
     *
     * Return true if such a permutation exists, otherwise return false.
     *
     * Note:
     *
     * The factorial of a non-negative integer x, denoted as x!, is the product of all positive integers less than or
     * equal to x, and 0! = 1.
     * A permutation is a rearrangement of all the digits of a number that does not start with zero. Any arrangement
     * starting with zero is invalid.
     *
     * Input: n = 145
     * Output: true
     *
     * Input: n = 10
     * Output: false
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public boolean isDigitorialPermutation(int n) {
        int[] fac = new int[10];
        fac[0] = 1;
        for (int i = 1; i < 10; i++) fac[i] = fac[i - 1] * i;
        int[] cnt = get(n);
        int s = 0;
        for (int i = 0; i < 10; i++) s += cnt[i] * fac[i];
        int[] cnt2 = get(s);
        for (int i = 0; i < 10; i++) {
            if (cnt[i] != cnt2[i]) return false;
        }
        return true;
    }

    private int[] get(int x) {
        int[] cnt = new int[10];
        while (x > 0) {
            cnt[x % 10]++;
            x /= 10;
        }
        return cnt;
    }
}