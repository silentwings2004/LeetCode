package LC3601_3900;

public class LC3758_ConvertNumberWordstoDigits {
    /**
     * You are given a string s consisting of lowercase English letters. s may contain valid concatenated English words
     * representing the digits 0 to 9, without spaces.
     *
     * Your task is to extract each valid number word in order and convert it to its corresponding digit, producing a
     * string of digits.
     *
     * Parse s from left to right. At each position:
     *
     * If a valid number word starts at the current position, append its corresponding digit to the result and advance
     * by the length of that word.
     * Otherwise, skip exactly one character and continue parsing.
     * Return the resulting digit string. If no number words are found, return an empty string.
     *
     * Input: s = "onefourthree"
     * Output: "143"
     *
     * Input: s = "ninexsix"
     * Output: "96"
     *
     * Input: s = "zeero"
     * Output: ""
     *
     * Input: s = "tw"
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s contains only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public String convertNumber(String s) {
        String[] strs = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                int m = strs[j].length();
                if (i + m > n) continue;
                if (s.substring(i, i + m).equals(strs[j])) {
                    sb.append(j);
                    i += m - 1;
                    break;
                }
            }
        }
        return sb.toString();
    }
}