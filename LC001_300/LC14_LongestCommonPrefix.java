package LC001_300;
import java.util.*;
public class LC14_LongestCommonPrefix {
    /**
     * Write a function to find the longest common prefix string amongst an array of strings.
     *
     * If there is no common prefix, return an empty string "".
     *
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     *
     * Constraints:
     *
     * 0 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] consists of only lower-case English letters.
     * @param strs
     * @return
     */
    // time = O(m * n), space = O(m)
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;; i++) {
            if (strs[0].length() <= i) return sb.toString();
            char c = strs[0].charAt(i);
            for (String str : strs) {
                if (str.length() <= i || str.charAt(i) != c) return sb.toString();
            }
            sb.append(c);
        }
    }

    // S2
    // time = O(m * n * log(m)), space = O(1)
    public String longestCommonPrefix2(String[] strs) {
        int min = 200;
        for (String s : strs) min = Math.min(min, s.length());

        int l = 0, r = min;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(strs, mid)) l = mid;
            else r = mid - 1;
        }
        return strs[0].substring(0, r);
    }

    private boolean check(String[] strs, int t) {
        String s = strs[0].substring(0, t);
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].indexOf(s) != 0) return false;
        }
        return true;
    }
}