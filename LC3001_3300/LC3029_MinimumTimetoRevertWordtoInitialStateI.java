package LC3001_3300;

public class LC3029_MinimumTimetoRevertWordtoInitialStateI {
    /**
     * You are given a 0-indexed string word and an integer k.
     *
     * At every second, you must perform the following operations:
     *
     * Remove the first k characters of word.
     * Add any k characters to the end of word.
     * Note that you do not necessarily need to add the same characters that you removed. However, you must perform both
     * operations at every second.
     *
     * Return the minimum time greater than zero required for word to revert to its initial state.
     *
     * Input: word = "abacaba", k = 3
     * Output: 2
     *
     * Input: word = "abacaba", k = 4
     * Output: 1
     *
     * Input: word = "abcbabcd", k = 2
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= word.length <= 50
     * 1 <= k <= word.length
     * word consists only of lowercase English letters.
     * @param word
     * @param k
     * @return
     */
    // time = O(n^2), space = O(n)
    public int minimumTimeToInitialState(String word, int k) {
        int n = word.length(), t = (n + k - 1) / k, res = t;
        for (int i = 1; i < t; i++) {
            String s = word.substring(k * i);
            boolean flag = true;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) != word.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res = i;
                break;
            }
        }
        return res;
    }
}