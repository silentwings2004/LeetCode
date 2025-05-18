package LC1201_1500;

public class LC1256_EncodeNumber {
    /**
     * Given a non-negative integer num, Return its encoding string.
     *
     * The encoding is done by converting the integer to a string using a secret function that you should deduce from
     * the following table:
     *
     * Input: num = 23
     * Output: "1000"
     *
     * Input: num = 107
     * Output: "101100"
     *
     * Constraints:
     *
     * 0 <= num <= 10^9
     * @param num
     * @return
     */
    // time = O(1), space = O(1)
    public String encode(int num) {
        String s = Integer.toBinaryString(num + 1);
        return s.substring(1);
    }
}