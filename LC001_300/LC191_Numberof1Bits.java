package LC001_300;
import java.util.*;
public class LC191_Numberof1Bits {
    /**
     * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the
     * Hamming weight).
     *
     * Note:
     *
     * Note that in some languages, such as Java, there is no unsigned integer type. In this case, the input will be
     * given as a signed integer type. It should not affect your implementation, as the integer's internal binary
     * representation is the same, whether it is signed or unsigned.
     * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3, the
     * input represents the signed integer. -3.
     *
     * Input: n = 00000000000000000000000000001011
     * Output: 3
     *
     * Constraints:
     *
     * The input must be a binary string of length 32.
     *
     *
     * Follow up: If this function is called many times, how would you optimize it?
     * @param n
     * @return
     */
    // you need to treat n as an unsigned value
    // S1
    // time = O(1), space = O(1)
    public int hammingWeight(int n) {
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            if ((n >> i & 1) == 1) cnt++;
        }
        return cnt;
    }

    // S2: low bit
    // time = O(1), space = O(1)
    public int hammingWeight2(int n) {
        int cnt = 0;
        while (n != 0) {
            n -= lowbit(n);
            cnt++;
        }
        return cnt;
    }

    private int lowbit(int x) {
        return x & -x;
    }
}