package LC1201_1500;
import java.util.*;
public class LC1324_PrintWordsVertically {
    /**
     * Given a string s. Return all the words vertically in the same order in which they appear in s.
     * Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
     * Each word would be put on only one column and that in one column there will be only one word.
     *
     * Input: s = "HOW ARE YOU"
     * Output: ["HAY","ORO","WEU"]
     *
     * Input: s = "TO BE OR NOT TO BE"
     * Output: ["TBONTB","OEROOE","   T"]
     *
     * Input: s = "CONTEST IS COMING"
     * Output: ["CIC","OSO","N M","T I","E N","S G","T"]
     *
     * Constraints:
     *
     * 1 <= s.length <= 200
     * s contains only upper case English letters.
     * It's guaranteed that there is only one space between 2 words.
     * @param s
     * @return
     */
    // time = O(n * max(S)), space = O(n * max(S))
    public List<String> printVertically(String s) {
        List<String> res = new ArrayList<>();
        String[] strs = s.split(" ");
        int n = strs.length, maxLen = 0;
        for (String t : strs) maxLen = Math.max(maxLen, t.length());
        for (int i = 0; i < maxLen; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (i < strs[j].length()) sb.append(strs[j].charAt(i));
                else sb.append(' ');
            }
            while (sb.charAt(sb.length() - 1) == ' ') sb.setLength(sb.length() - 1);
            res.add(sb.toString());
        }
        return res;
    }
}