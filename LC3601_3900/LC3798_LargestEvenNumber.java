package LC3601_3900;

public class LC3798_LargestEvenNumber {
    /**
     * You are given a string s consisting only of the characters '1' and '2'.
     *
     * You may delete any number of characters from s without changing the order of the remaining characters.
     *
     * Return the largest possible resultant string that represents an even integer. If there is no such string, return
     * the empty string "".
     *
     * Input: s = "1112"
     * Output: "1112"
     *
     * Input: s = "221"
     * Output: "22"
     *
     * Input: s = "1"
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists only of the characters '1' and '2'.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public String largestEven(String s) {
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '2') return s.substring(0, i + 1);
        }
        return "";
    }
}