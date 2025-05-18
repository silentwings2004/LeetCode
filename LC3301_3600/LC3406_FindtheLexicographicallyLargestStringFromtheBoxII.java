package LC3301_3600;

public class LC3406_FindtheLexicographicallyLargestStringFromtheBoxII {
    /**
     * You are given a string word, and an integer numFriends.
     *
     * Alice is organizing a game for her numFriends friends. There are multiple rounds in the game, where in each round:
     *
     * word is split into numFriends non-empty strings, such that no previous round has had the exact same split.
     * All the split words are put into a box.
     * Find the lexicographically largest string from the box after all the rounds are finished.
     *
     * A string a is lexicographically smaller than a string b if in the first position where a and b differ, string a
     * has a letter that appears earlier in the alphabet than the corresponding letter in b.
     * If the first min(a.length, b.length) characters do not differ, then the shorter string is the lexicographically
     * smaller one.
     *
     * Input: word = "dbca", numFriends = 2
     * Output: "dbc"
     *
     * Input: word = "gggg", numFriends = 4
     * Output: "g"
     *
     * Constraints:
     *
     * 1 <= word.length <= 2 * 10^5
     * word consists only of lowercase English letters.
     * 1 <= numFriends <= word.length
     * @param word
     * @param numFriends
     * @return
     */
    // time = O(n), space = O(1)
    public String answerString(String word, int numFriends) {
        if (numFriends == 1) return word;
        int n = word.length(), i = 0, j = 1;
        while (j < n) {
            int k = 0;
            while (j + k < n && word.charAt(i + k) == word.charAt(j + k)) k++;
            if (j + k < n && word.charAt(i + k) < word.charAt(j + k)) {
                int t = i;
                i = j;
                j = Math.max(j + 1, t + k + 1);
            } else j += k + 1;
        }
        return word.substring(i, Math.min(i + n - numFriends + 1, n));
    }
}
