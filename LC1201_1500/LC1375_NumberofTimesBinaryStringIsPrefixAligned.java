package LC1201_1500;

public class LC1375_NumberofTimesBinaryStringIsPrefixAligned {
    /**
     * You have a 1-indexed binary string of length n where all the bits are 0 initially. We will flip all the bits of
     * this binary string (i.e., change them from 0 to 1) one by one. You are given a 1-indexed integer array flips
     * where flips[i] indicates that the bit at index i will be flipped in the ith step.
     *
     * A binary string is prefix-aligned if, after the ith step, all the bits in the inclusive range [1, i] are ones and
     * all the other bits are zeros.
     *
     * Return the number of times the binary string is prefix-aligned during the flipping process.
     *
     * Input: flips = [3,2,4,1,5]
     * Output: 2
     *
     * Input: flips = [4,1,2,3]
     * Output: 1
     *
     * Constraints:
     *
     * n == flips.length
     * 1 <= n <= 5 * 10^4
     * flips is a permutation of the integers in the range [1, n].
     * @param flips
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int numTimesAllBlue(int[] flips) {
        int n = flips.length, res = 0;
        boolean[] st = new boolean[n];
        for (int i = 0, j = 0; i < n; i++) {
            int x = flips[i];
            st[x - 1] = true;
            while (j < n && st[j]) j++;
            if (j == i + 1) res++;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int numTimesAllBlue2(int[] flips) {
        int n = flips.length, res = 0, k = -1;
        for (int i = 0; i < n; i++) {
            k = Math.max(k, flips[i]);
            if (k == i + 1) res++;
        }
        return res;
    }
}