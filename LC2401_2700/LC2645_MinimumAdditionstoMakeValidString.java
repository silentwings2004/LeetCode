package LC2401_2700;

public class LC2645_MinimumAdditionstoMakeValidString {
    /**
     * Given a string word to which you can insert letters "a", "b" or "c" anywhere and any number of times, return the
     * minimum number of letters that must be inserted so that word becomes valid.
     *
     * A string is called valid if it can be formed by concatenating the string "abc" several times.
     *
     * Input: word = "b"
     * Output: 2
     *
     * Input: word = "aaa"
     * Output: 6
     *
     * Input: word = "abc"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= word.length <= 50
     * word consists of letters "a", "b" and "c" only.
     * @param word
     * @return
     */
    // time = O(n), space = O(1)
    public int addMinimum(String word) {
        int n = word.length(), res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && word.charAt(j) > word.charAt(j - 1)) j++;
            int len = j - i;
            res += 3 - len;
            i = j - 1;
        }
        return res;
    }
}