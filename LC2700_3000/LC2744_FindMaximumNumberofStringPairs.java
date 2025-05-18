package LC2700_3000;

public class LC2744_FindMaximumNumberofStringPairs {
    /**
     * You are given a 0-indexed array words consisting of distinct strings.
     *
     * The string words[i] can be paired with the string words[j] if:
     *
     * The string words[i] is equal to the reversed string of words[j].
     * 0 <= i < j < words.length.
     * Return the maximum number of pairs that can be formed from the array words.
     *
     * Note that each string can belong in at most one pair.
     *
     * Input: words = ["cd","ac","dc","ca","zz"]
     * Output: 2
     *
     * Input: words = ["ab","ba","cc"]
     * Output: 1
     *
     * Input: words = ["aa","ab"]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= words.length <= 50
     * words[i].length == 2
     * words consists of distinct strings.
     * words[i] contains only lowercase English letters.
     * @param words
     * @return
     */
    // time = O(n^2), space = O(1)
    public int maximumNumberOfStringPairs(String[] words) {
        int n = words.length, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (words[i].charAt(0) == words[j].charAt(1) && words[i].charAt(1) == words[j].charAt(0)) res++;
            }
        }
        return res;
    }
}