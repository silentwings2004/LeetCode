package LC1201_1500;

public class LC1318_MinimumFlipstoMakeaORbEqualtoc {
    /**
     * Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR
     * b == c ). (bitwise OR operation).
     * Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.
     *
     * Input: a = 2, b = 6, c = 5
     * Output: 3
     *
     * Input: a = 4, b = 2, c = 7
     * Output: 1
     *
     * Input: a = 1, b = 2, c = 3
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= a <= 10^9
     * 1 <= b <= 10^9
     * 1 <= c <= 10^9
     * @param a
     * @param b
     * @param c
     * @return
     */
    // time = O(1), space = O(1)
    public int minFlips(int a, int b, int c) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int x = a >> i & 1, y = b >> i & 1, z = c >> i & 1;
            if ((x | y) == z) continue;
            else if (z == 1) res++;
            else {
                if (x == 1 && y == 1) res += 2;
                else res++;
            }
        }
        return res;
    }
}