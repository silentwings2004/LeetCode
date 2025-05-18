package LC2401_2700;

public class LC2595_NumberofEvenandOddBits {
    /**
     * You are given a positive integer n.
     *
     * Let even denote the number of even indices in the binary representation of n (0-indexed) with value 1.
     *
     * Let odd denote the number of odd indices in the binary representation of n (0-indexed) with value 1.
     *
     * Return an integer array answer where answer = [even, odd].
     *
     * Input: n = 17
     * Output: [2,0]
     *
     * Input: n = 2
     * Output: [0,1]
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * @param n
     * @return
     */
    // time = O(m), space = O(1)
    public int[] evenOddBit(int n) {
        int a = 0, b = 0;
        int m = Integer.toBinaryString(n).length();
        for (int i = 0; i < m; i++) {
            if ((n >> i & 1) == 1) {
                if (i % 2 == 0) a++;
                else b++;
            }
        }
        return new int[]{a, b};
    }
}