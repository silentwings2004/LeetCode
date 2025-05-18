package LC601_900;
import java.util.*;
public class LC830_PositionsofLargeGroups {
    /**
     * In a string s of lowercase letters, these letters form consecutive groups of the same character.
     *
     * For example, a string like s = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z", and "yy".
     *
     * A group is identified by an interval [start, end], where start and end denote the start and end indices
     * (inclusive) of the group. In the above example, "xxxx" has the interval [3,6].
     *
     * A group is considered large if it has 3 or more characters.
     *
     * Return the intervals of every large group sorted in increasing order by start index.
     *
     * Input: s = "abbxxxxzzy"
     * Output: [[3,6]]
     *
     * Input: s = "abc"
     * Output: []
     *
     * Input: s = "abcdddeeeeaabbbcd"
     * Output: [[3,5],[6,9],[12,14]]
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s contains lowercase English letters only.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;
            if (j - i >= 3) res.add(Arrays.asList(i, j - 1));
            i = j - 1;
        }
        return res;
    }
}