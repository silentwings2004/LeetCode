package LC2101_2400;
import java.util.*;
public class LC2182_ConstructStringWithRepeatLimit {
    /**
     * You are given a string s and an integer repeatLimit. Construct a new string repeatLimitedString using the
     * characters of s such that no letter appears more than repeatLimit times in a row. You do not have to use all
     * characters from s.
     *
     * Return the lexicographically largest repeatLimitedString possible.
     *
     * A string a is lexicographically larger than a string b if in the first position where a and b differ, string a
     * has a letter that appears later in the alphabet than the corresponding letter in b. If the first
     * min(a.length, b.length) characters do not differ, then the longer string is the lexicographically larger one.
     *
     * Input: s = "cczazcc", repeatLimit = 3
     * Output: "zzcccac"
     *
     * Constraints:
     *
     * 1 <= repeatLimit <= s.length <= 10^5
     * s consists of lowercase English letters.
     * @param s
     * @param repeatLimit
     * @return
     */
    // time = O(n), space = O(n)
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 25; i >= 0; i--) {
            int k = i - 1;
            while (true) {
                for (int j = 0; j < repeatLimit && cnt[i] > 0; j++) {
                    cnt[i]--;
                    sb.append((char)(i + 'a'));
                }
                if (cnt[i] == 0) break;
                while (k >= 0 && cnt[k] == 0) k--;
                if (k < 0) break;
                cnt[k]--;
                sb.append((char)(k + 'a'));
            }
        }
        return sb.toString();
    }
}