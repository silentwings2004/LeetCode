package LC301_600;
import java.util.*;
public class LC387_FirstUniqueCharacterinaString {
    /**
     * Given a string s, find the first non-repeating character in it and return its index. If it does not exist,
     * return -1.
     *
     * Input: s = "leetcode"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of only lowercase English letters.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) count[s.charAt(i) - 'a']++;

        int res = -1;
        for (int i = 0; i < n; i++) {
            if (count[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }

    // S2: LinkedHashMap (stream flow)
    // time = O(n), space = O(n)
    public int firstUniqChar2(String s) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        HashSet<Character> set = new HashSet<>();

        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                if (map.get(c) != null) map.remove(c);
            } else {
                map.put(c, i);
                set.add(c);
            }
        }
        return map.size() == 0 ? -1 : map.entrySet().iterator().next().getValue();
    }

    // S3: lastIndexOf
    // time = O(1), space = O(1)
    public int firstUniqChar3(String s) {
        int n = s.length(), res = n;
        for (char c = 'a'; c <= 'z'; c++) {
            int idx = s.indexOf(c);
            if (idx == -1) continue;
            if (idx == s.lastIndexOf(c)) res = Math.min(res, idx);
        }
        return res == n ? -1 : res;
    }
}
