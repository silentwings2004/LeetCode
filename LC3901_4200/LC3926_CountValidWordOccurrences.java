package LC3901_4200;
import java.util.*;
public class LC3926_CountValidWordOccurrences {
    /**
     * You are given an array of strings chunks. The strings are concatenated in order to form a single string s.
     *
     * You are also given an array of strings queries.
     *
     * A word is defined as a substring of s that:
     *
     * consists of lowercase English letters ('a' to 'z'),
     * may include hyphens ('-') only if each hyphen is surrounded by lowercase English letters, and
     * is not part of a longer substring that also satisfies the above conditions.
     * Any character that is not a lowercase English letter or a valid hyphen acts as a separator.
     *
     * Return an integer array ans such that ans[i] is the number of occurrences of queries[i] as a word in s.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: chunks = ["hello wor","ld hello"], queries = ["hello","world","wor"]
     * Output: [2,1,0]
     *
     * Input: chunks = ["a--b a-","-c"], queries = ["a","b","c"]
     * Output: [2,1,1]
     *
     * Input: chunks = ["hello"], queries = ["hello","ell"]
     * Output: [1,0]
     *
     * Constraints:
     *
     * 1 <= chunks.length <= 105
     * 1 <= chunks[i].length <= 10^5
     * chunks[i] may consist of lowercase English letters, spaces, and hyphens.
     * The total length of all strings in chunks does not exceed 105
     * 1 <= queries.length <= 105
     * 1 <= queries[i].length <= 10^5
     * queries[i] is a valid word
     * The total length of all strings in queries does not exceed 105
     * @param chunks
     * @param queries
     * @return
     */
    // time = O(n + m), space = O(n + m)
    public int[] countWordOccurrences(String[] chunks, String[] queries) {
        StringBuilder sb = new StringBuilder();
        for (String s : chunks) sb.append(s);
        String s = sb.toString();
        int n = s.length();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            sb = new StringBuilder();
            int j = i;
            while (j < n) {
                c = s.charAt(j);
                if (Character.isLowerCase(c)) sb.append(c);
                else if (c == '-') {
                    if (j == 0 || j == n - 1) break;
                    char c1 = s.charAt(j - 1);
                    char c2 = s.charAt(j + 1);
                    if (Character.isLowerCase(c1) && Character.isLowerCase(c2)) sb.append(c);
                    else break;
                } else break;
                j++;
            }
            String t = sb.toString();
            map.put(t, map.getOrDefault(t, 0) + 1);
            i = j;
        }

        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            String q = queries[i];
            res[i] = map.getOrDefault(q, 0);
        }
        return res;
    }
}