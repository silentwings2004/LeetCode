package LC1501_1800;

public class LC1592_RearrangeSpacesBetweenWords {
    /**
     * You are given a string text of words that are placed among some number of spaces. Each word consists of one or
     * more lowercase English letters and are separated by at least one space. It's guaranteed that text contains at
     * least one word.
     *
     * Rearrange the spaces so that there is an equal number of spaces between every pair of adjacent words and that
     * number is maximized. If you cannot redistribute all the spaces equally, place the extra spaces at the end,
     * meaning the returned string should be the same length as text.
     *
     * Return the string after rearranging the spaces.
     *
     * Input: text = "  this   is  a sentence "
     * Output: "this   is   a   sentence"
     *
     * Input: text = " practice   makes   perfect"
     * Output: "practice   makes   perfect "
     *
     * Constraints:
     *
     * 1 <= text.length <= 100
     * text consists of lowercase English letters and ' '.
     * text contains at least one word.
     * @param text
     * @return
     */
    // time = O(n), space = O(n)
    public String reorderSpaces(String text) {
        int n = text.length(), spaces = 0, words = 0;
        for (int i = 0; i < n; i++) {
            if (text.charAt(i) != ' ') continue;
            int j = i;
            while (j < n && text.charAt(j) == ' ') j++;
            spaces += j - i;
            i = j - 1;
        }
        StringBuilder sb = new StringBuilder();
        String[] strs = text.trim().split("\\s+");
        n = strs.length;
        if (n > 1) {
            int x = spaces / (n - 1);
            for (int i = 0; i < n - 1; i++) {
                sb.append(strs[i]);
                for (int k = 0; k < x; k++) sb.append(' ');
            }
            sb.append(strs[n - 1]);
            for (int i = 0; i < spaces - (n - 1) * x; i++) sb.append(' ');
        } else {
            sb.append(strs[0]);
            for (int i = 0; i < spaces; i++) sb.append(' ');
        }
        return sb.toString();
    }
}
