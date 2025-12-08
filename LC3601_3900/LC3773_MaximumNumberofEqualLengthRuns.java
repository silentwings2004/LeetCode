package LC3601_3900;
import java.util.*;
public class LC3773_MaximumNumberofEqualLengthRuns {
    /**
     * You are given a string s consisting of lowercase English letters.
     *
     * A run in s is a substring of equal letters that cannot be extended further. For example, the runs in "hello" are
     * "h", "e", "ll", and "o".
     *
     * You can select runs that have the same length in s.
     *
     * Return an integer denoting the maximum number of runs you can select in s.
     *
     * Input: s = "hello"
     * Output: 3
     *
     * Input: s = "aaabaaa"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of lowercase English letters only.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public int maxSameLengthRuns(String s) {
        int n = s.length();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;
            int len = j - i;
            map.put(len, map.getOrDefault(len, 0) + 1);
            i = j - 1;
        }
        int res = 0;
        for (int v : map.values()) res = Math.max(res, v);
        return res;
    }
}