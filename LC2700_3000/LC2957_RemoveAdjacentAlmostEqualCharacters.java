package LC2700_3000;

public class LC2957_RemoveAdjacentAlmostEqualCharacters {
    /**
     * You are given a 0-indexed string word.
     *
     * In one operation, you can pick any index i of word and change word[i] to any lowercase English letter.
     *
     * Return the minimum number of operations needed to remove all adjacent almost-equal characters from word.
     *
     * Two characters a and b are almost-equal if a == b or a and b are adjacent in the alphabet.
     *
     * Input: word = "aaaaa"
     * Output: 2
     *
     * Input: word = "abddez"
     * Output: 2
     *
     * Input: word = "zyxyxyz"
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= word.length <= 100
     * word consists only of lowercase English letters.
     * @param word
     * @return
     */
    // time = O(n), space = O(1)
    public int removeAlmostEqualCharacters(String word) {
        int n = word.length(), res = 0;
        for (int i = 0; i < n; i++) {
            if (i + 1 < n && Math.abs(word.charAt(i) - word.charAt(i + 1)) <= 1) {
                res++;
                i++;
            }
        }
        return res;
    }
}
/**
 * 两个相邻字母，如果近似相等，那么就改右边那个
 */