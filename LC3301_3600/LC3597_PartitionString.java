package LC3301_3600;
import java.util.*;
public class LC3597_PartitionString {
    /**
     * Given a string s, partition it into unique segments according to the following procedure:
     *
     * Start building a segment beginning at index 0.
     * Continue extending the current segment character by character until the current segment has not been seen before.
     * Once the segment is unique, add it to your list of segments, mark it as seen, and begin a new segment from the
     * next index.
     * Repeat until you reach the end of s.
     * Return an array of strings segments, where segments[i] is the ith segment created.
     *
     * Input: s = "abbccccd"
     * Output: ["a","b","bc","c","cc","d"]
     *
     * Input: s = "aaaa"
     * Output: ["a","aa"]
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s contains only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public List<String> partitionString(String s) {
        List<String> res = new ArrayList<>();
        int n = s.length();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            boolean f = false;
            for (int j = i; j < n; j++) {
                sb.append(s.charAt(j));
                String t = sb.toString();
                if (set.add(t)) {
                    res.add(t);
                    f = true;
                    i = j;
                    break;
                }
            }
            if (!f) break;
        }
        return res;
    }
}