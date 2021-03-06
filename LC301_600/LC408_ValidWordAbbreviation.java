package LC301_600;
import java.util.*;
public class LC408_ValidWordAbbreviation {
    /**
     * A string can be abbreviated by replacing any number of non-adjacent substrings with their lengths. For example,
     * a string such as "substitution" could be abbreviated as (but not limited to):
     *
     * "s10n" ("s ubstitutio n")
     * "sub4u4" ("sub stit u tion")
     * "12" ("substitution")
     * "su3i1u2on" ("su bst i t u ti on")
     * "substitution" (no substrings replaced)
     * Note that "s55n" ("s ubsti tutio n") is not a valid abbreviation of "substitution" because the replaced
     * substrings are adjacent.
     *
     * Given a string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
     *
     * Input: word = "internationalization", abbr = "i12iz4n"
     * Output: true
     *
     * Constraints:
     *
     * 1 <= word.length, abbr.length <= 20
     * word consists of only lowercase English letters.
     * abbr consists of lowercase English letters and digits.
     * @param word
     * @param abbr
     * @return
     */
    // time = O(n), space = O(1)
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        int m = word.length(), n = abbr.length();
        while (i < m && j < n) {
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }
            if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') return false;

            int start = j;
            while (j < n && Character.isDigit(abbr.charAt(j))) j++;
            int len = Integer.parseInt(abbr.substring(start, j));
            i += len;
        }
        return i == m && j == n;
    }
}
