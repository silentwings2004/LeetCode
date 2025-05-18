package LC901_1200;
import java.util.*;
public class LC1023_CamelcaseMatching {
    /**
     * Given an array of strings queries and a string pattern, return a boolean array answer where answer[i] is true if
     * queries[i] matches pattern, and false otherwise.
     *
     * A query word queries[i] matches pattern if you can insert lowercase English letters pattern so that it equals the
     * query. You may insert each character at any position and you may not insert any characters.
     *
     * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
     * Output: [true,false,true,true,false]
     *
     * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
     * Output: [true,false,true,false,false]
     *
     * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
     * Output: [false,true,false,false,false]
     *
     * Constraints:
     *
     * 1 <= pattern.length, queries.length <= 100
     * 1 <= queries[i].length <= 100
     * queries[i] and pattern consist of English letters.
     * @param queries
     * @param pattern
     * @return
     */
    // time = O(m * k), space = O(1)
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList<>();
        for (String q : queries) res.add(check(q, pattern));
        return res;
    }

    private boolean check(String a, String b) {
        int j = 0;
        for (int i = 0; i < a.length(); i++) {
            if (j < b.length() && a.charAt(i) == b.charAt(j)) j++;
            else if (a.charAt(i) < 'a') return false;
        }
        return j == b.length();
    }
}