package LC3001_3300;
import java.util.*;
public class LC3137_MinimumNumberofOperationstoMakeWordKPeriodic {
    /**
     * You are given a string word of size n, and an integer k such that k divides n.
     *
     * In one operation, you can pick any two indices i and j, that are divisible by k, then replace the
     * substring of length k starting at i with the substring of length k starting at j. That is, replace the substring
     *  word[i..i + k - 1] with the substring word[j..j + k - 1].
     *
     * Return the minimum number of operations required to make word k-periodic.
     *
     * We say that word is k-periodic if there is some string s of length k such that word can be obtained by
     * concatenating s an arbitrary number of times. For example, if word == “ababab”, then word is 2-periodic for
     * s = "ab".
     *
     * Input: word = "leetcodeleet", k = 4
     * Output: 1
     *
     * Input: word = "leetcoleet", k = 2
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n == word.length <= 10^5
     * 1 <= k <= word.length
     * k divides word.length.
     * word consists only of lowercase English letters.
     * @param word
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        int n = word.length(), cnt = n / k;
        for (int i = 0; i + k <= n; i += k) {
            String t = word.substring(i, i + k);
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        int mc = 0;
        for (int v : map.values()) mc = Math.max(mc, v);
        return cnt - mc;
    }
}