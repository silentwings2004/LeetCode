package LC3301_3600;

public class LC3403_FindtheLexicographicallyLargestStringFromtheBoxI {
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
     * 1 <= word.length <= 5 * 10^3
     * word consists only of lowercase English letters.
     * 1 <= numFriends <= word.length
     * @param word
     * @param numFriends
     * @return
     */
    // S1
    // time = O(n*(n - k)), space = O(n - k)
    public String answerString(String word, int numFriends) {
        int n = word.length(), m = numFriends - 1, mc = 0;
        if (m == 0) return word;
        String res = "";
        for (int i = 0; i < n; i++) {
            int u = word.charAt(i) - 'a';
            if (u >= mc) {
                int d = Math.max(0, m - i), len = Math.max(0, n - i - d);
                String s = word.substring(i, i + len);
                if (s.compareTo(res) > 0) res = s;
                mc = u;
            }
        }
        return res;
    }

    // S2
    // time = O(n*(n - k)), space = O(n - k)
    public String answerString2(String word, int numFriends) {
        if (numFriends == 1) return word;
        int n = word.length(), len = n - numFriends + 1;
        String res = "";
        for (int i = 0; i < n; i++) {
            int j = Math.min(i + len - 1, n - 1);
            String s = word.substring(i, j + 1);
            if (s.compareTo(res) > 0) res = s;
        }
        return res;
    }
}
/**
 * 1. 当子串的左端点固定的时候，子串越长(右端点越大),子串的字典序就越大
 * 2. 枚举左端点，右端点 +1 为 min(i + n - k + 1, n)
 * 3. 特判 k = 1 的情况，直接返回 s 就好了
 */