package LC3901_4200;
import java.util.*;
public class LC4106_FindMismatchedSegmentsBetweenTwoStrings {
    /**
     * You are given two strings s1 and s2 of the same length.
     *
     * A mismatched segment is a maximal contiguous range of indices where the characters of s1 and s2 differ at every
     * index.
     *
     * Return a 2D array containing [sub1, sub2] for each mismatched segment, where sub1 and sub2 are the corresponding
     * substrings from s1 and s2, respectively. Return the segments in increasing order of their starting indices. If
     * there are no mismatched segments, return an empty array.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s1 = "earth", s2 = "empty"
     * Output: [["ar","mp"],["h","y"]]
     *
     * Input: s1 = "action", s2 = "nation"
     * Output: [["ac","na"]]
     *
     * Input: s1 = "hot", s2 = "hot"
     * Output: []
     *
     * Constraints:
     *
     * 1 <= s1.length == s2.length <= 100
     * s1 and s2 consist of lowercase English letters.
     * @param s1
     * @param s2
     * @return
     */
    // time = O(n), space = O(1)
    public List<List<String>> findMismatchedSegments(String s1, String s2) {
        List<List<String>> res = new ArrayList<>();
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) == s2.charAt(i)) continue;
            int j = i + 1;
            while (j < n && s1.charAt(j) != s2.charAt(j)) j++;
            res.add(Arrays.asList(s1.substring(i, j), s2.substring(i, j)));
            i = j;
        }
        return res;
    }
}