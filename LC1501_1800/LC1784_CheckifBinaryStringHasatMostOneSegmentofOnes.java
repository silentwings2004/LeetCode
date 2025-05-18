package LC1501_1800;

public class LC1784_CheckifBinaryStringHasatMostOneSegmentofOnes {
    /**
     * Given a binary string s without leading zeros, return true if s contains at most
     * one contiguous segment of ones. Otherwise, return false.
     *
     * Input: s = "1001"
     * Output: false
     *
     * Input: s = "110"
     * Output: true
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s[i] is either '0' or '1'.
     * s[0] is '1'.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public boolean checkOnesSegment(String s) {
        int n = s.length();
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                if (flag) return false;
                flag = true;
                int j = i + 1;
                while (j < n && s.charAt(j) == '1') j++;
                i = j - 1;
            }
        }
        return true;
    }
}
