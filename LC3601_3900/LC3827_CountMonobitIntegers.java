package LC3601_3900;

public class LC3827_CountMonobitIntegers {
    /**
     * You are given an integer n.
     *
     * An integer is called Monobit if all bits in its binary representation are the same.
     *
     * Return the count of Monobit integers in the range [0, n] (inclusive).
     *
     * Input: n = 1
     * Output: 2
     *
     * Input: n = 4
     * Output: 3
     *
     * Constraints:
     *
     * 0 <= n <= 1000
     * @param n
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public int countMonobit(int n) {
        int res = 0, x = 0, i = 0;
        while (x <= n) {
            res++;
            x |= 1 << i;
            i++;
        }
        return res;
    }

    // S2
    // time = O(1), space = O(1)
    public int countMonobit2(int n) {
        return 32 - Integer.numberOfLeadingZeros(n + 1);
    }
}
