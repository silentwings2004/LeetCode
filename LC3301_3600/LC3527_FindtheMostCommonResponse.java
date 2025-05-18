package LC3301_3600;
import java.util.*;
public class LC3527_FindtheMostCommonResponse {
    /**
     * You are given a 2D string array responses where each responses[i] is an array of strings representing survey
     * responses from the ith day.
     *
     * Return the most common response across all days after removing duplicate responses within each responses[i]. If
     * there is a tie, return the lexicographically smallest response.
     *
     * A string a is lexicographically smaller than a string b if in the first position where a and b differ, string a
     * has a letter that appears earlier in the alphabet than the corresponding letter in b.
     * If the first min(a.length, b.length) characters do not differ, then the shorter string is the lexicographically
     * smaller one.
     *
     * Input: responses = [["good","ok","good","ok"],["ok","bad","good","ok","ok"],["good"],["bad"]]
     * Output: "good"
     *
     * Input: responses = [["good","ok","good"],["ok","bad"],["bad","notsure"],["great","good"]]
     * Output: "bad"
     *
     * Constraints:
     *
     * 1 <= responses.length <= 1000
     * 1 <= responses[i].length <= 1000
     * 1 <= responses[i][j].length <= 10
     * responses[i][j] consists of only lowercase English letters
     * @param responses
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public String findCommonResponse(List<List<String>> responses) {
        HashMap<String, Integer> map = new HashMap<>();
        int mx = 0;
        for (List<String> r : responses) {
            r = new ArrayList<>(new HashSet<>(r));
            for (String s : r) {
                map.put(s, map.getOrDefault(s, 0) + 1);
                mx = Math.max(mx, map.get(s));
            }
        }

        String res = "";
        for (String s : map.keySet()) {
            if (map.get(s) == mx) {
                if (res.length() == 0 || s.compareTo(res) < 0) res = s;
            }
        }
        return res;
    }
}