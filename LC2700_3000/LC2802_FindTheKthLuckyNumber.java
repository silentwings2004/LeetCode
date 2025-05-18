package LC2700_3000;

public class LC2802_FindTheKthLuckyNumber {
    /**
     * We know that 4 and 7 are lucky digits. Also, a number is called lucky if it contains only lucky digits.
     *
     * You are given an integer k, return the kth lucky number represented as a string.
     *
     * Input: k = 4
     * Output: "47"
     *
     * Input: k = 10
     * Output: "477"
     *
     * Input: k = 1000
     * Output: "777747447"
     *
     * Constraints:
     *
     * 1 <= k <= 10^9
     * @param k
     * @return
     */
    // time = O(logk), space = O(logk)
    public String kthLuckyNumber(int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = k + 1; i != 1; i /= 2) sb.append((i & 1) == 0 ? '4' : '7');
        return sb.reverse().toString();
    }
}
/**
 * The tricky part is to see that it's actually the binary representation of k+1 not k that we need!
 */