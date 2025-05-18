package LC3001_3300;

public class LC3174_ClearDigits {
    /**
     * You are given a string s.
     *
     * Your task is to remove all digits by doing this operation repeatedly:
     *
     * Delete the first digit and the closest non-digit character to its left.
     * Return the resulting string after removing all digits.
     *
     * Input: s = "abc"
     *
     * Output: "abc"
     *
     * Input: s = "cb34"
     *
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists only of lowercase English letters and digits.
     * The input is generated such that it is possible to delete all digits.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String clearDigits(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(s.charAt(i))) {
                if (sb.length() > 0) sb.setLength(sb.length() - 1);
            } else sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}