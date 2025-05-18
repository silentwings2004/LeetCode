package LC2401_2700;

public class LC2429_MinimizeXOR {
    /**
     * Given two positive integers num1 and num2, find the integer x such that:
     *
     * x has the same number of set bits as num2, and
     * The value x XOR num1 is minimal.
     * Note that XOR is the bitwise XOR operation.
     *
     * Return the integer x. The test cases are generated such that x is uniquely determined.
     *
     * The number of set bits of an integer is the number of 1's in its binary representation.
     *
     * Input: num1 = 3, num2 = 5
     * Output: 3
     *
     * Input: num1 = 1, num2 = 12
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= num1, num2 <= 10^9
     * @param num1
     * @param num2
     * @return
     */
    // time = O(1), space = O(1)
    public int minimizeXor(int num1, int num2) {
        int cnt = Integer.bitCount(num2), res = 0;
        for (int i = 31; i >= 0; i--) {
            if ((num1 >> i & 1) == 1) {
                res |= 1 << i;
                cnt--;
                if (cnt == 0) break;
            }
        }

        if (cnt > 0) {
            for (int i = 0; i < 31; i++) {
                if ((res >> i & 1) == 0) {
                    res |= 1 << i;
                    cnt--;
                    if (cnt == 0) break;
                }
            }
        }
        return res;
    }
}
