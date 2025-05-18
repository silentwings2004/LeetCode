package LC2700_3000;
import java.util.*;
public class LC2950_NumberofDivisibleSubstrings {
    /**
     * Each character of the English alphabet has been mapped to a digit as shown below.
     *
     * A string is divisible if the sum of the mapped values of its characters is divisible by its length.
     *
     * Given a string s, return the number of divisible substrings of s.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: word = "asdf"
     * Output: 6
     *
     * Input: word = "bdh"
     * Output: 4
     *
     * Input: word = "abcd"
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= word.length <= 2000
     * word consists only of lowercase English letters.
     * @param word
     * @return
     */
    // time = O(n), space = O(n)
    public int countDivisibleSubstrings(String word) {
        int n = word.length(), res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < 10; i++) {
            map.clear();
            map.put(0, 1);
            for (int j = 0, s = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                s += 9 - ('z' - c) / 3 - i;
                res += map.getOrDefault(s, 0);
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
        return res;
    }
}