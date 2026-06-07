package LC3901_4200;

public class LC3950_ExactlyOneConsecutiveSetBitsPair {
    /**
     * You are given an integer n.
     *
     * Return true if its binary representation contains exactly one pair of consecutive set bits, and false otherwise.
     *
     * The set bits in an integer are the 1's present when it is written in binary.
     *
     * Input: nums = 6
     * Output: true
     *
     * Input: nums = 5
     * Output: false
     *
     * Constraints:
     *
     * 0 <= n <= 10^5
     * @param n
     * @return
     */
    // S1
    // time = O(logn), space = O(1)
    public boolean consecutiveSetBits(int n) {
        String s = Integer.toBinaryString(n);
        n = s.length();
        int cnt = 0;
        for (int i = 0; i + 1 < n; i++) {
            if (s.charAt(i) == '1' && s.charAt(i + 1) == '1') {
                cnt++;
                if (cnt > 1) return false;
            }
        }
        return cnt == 1;
    }

    // S2
    // time = O(logn), space = O(1)
    public boolean consecutiveSetBits2(int n) {
        int cnt = 0, len = 0;
        while (n > 0) {
            int x = n % 2;
            if (x == 1) {
                len++;
                if (len > 2) return false;
                else if (len == 2) cnt++;
            } else len = 0;
            n /= 2;
        }
        return cnt == 1;
    }

    // S3
    // time = O(1), space = O(1)
    public boolean consecutiveSetBits3(int n) {
        int m = n & (n >> 1); // 所有相邻比特位的 &
        return m > 0 && (m & (m - 1)) == 0; // m 是否恰好有一个 1
    }
}