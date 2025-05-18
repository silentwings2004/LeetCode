package LC2700_3000;
import java.util.*;
public class LC2868_TheWordingGame {
    /**
     * Alice and Bob each have a lexicographically sorted array of strings named a and b respectively.
     *
     * They are playing a wording game with the following rules:
     *
     * On each turn, the current player should play a word from their list such that the new word is closely greater
     * than the last played word; then it's the other player's turn.
     * If a player can't play a word on their turn, they lose.
     * Alice starts the game by playing her lexicographically smallest word.
     *
     * Given a and b, return true if Alice can win knowing that both players play their best, and false otherwise.
     *
     * A word w is closely greater than a word z if the following conditions are met:
     *
     * w is lexicographically greater than z.
     * If w1 is the first letter of w and z1 is the first letter of z, w1 should either be equal to z1 or be the letter
     * after z1 in the alphabet.
     * For example, the word "care" is closely greater than "book" and "car", but is not closely greater than "ant" or
     * "cook".
     * A string s is lexicographically greater than a string t if in the first position where s and t differ, string s
     * has a letter that appears later in the alphabet than the corresponding letter in t. If the first min(s.length,
     * t.length) characters do not differ, then the longer string is the lexicographically greater one.
     *
     * Input: a = ["avokado","dabar"], b = ["brazil"]
     * Output: false
     *
     * Input: a = ["ananas","atlas","banana"], b = ["albatros","cikla","nogomet"]
     * Output: true
     *
     * Input: a = ["hrvatska","zastava"], b = ["bijeli","galeb"]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= a.length, b.length <= 10^5
     * a[i] and b[i] consist only of lowercase English letters.
     * a and b are lexicographically sorted.
     * All the words in a and b combined are distinct.
     * The sum of the lengths of all the words in a and b combined does not exceed 10^6.
     * @param a
     * @param b
     * @return
     */
    // time = O(m + n), space = O(m + n)
    public boolean canAliceWin(String[] a, String[] b) {
        int m = a.length, n = b.length;
        List<String> qa = new ArrayList<>(), qb = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int j = i + 1;
            while (j < m && a[j].charAt(0) == a[i].charAt(0)) j++;
            qa.add(a[j - 1]);
            i = j - 1;
        }
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && b[j].charAt(0) == b[i].charAt(0)) j++;
            qb.add(b[j - 1]);
            i = j - 1;
        }

        m = qa.size();
        n = qb.size();
        int i = 0, j = 0;
        while (i < m && j < n) {
            String s = qa.get(i), t = qb.get(j);
            if (s.charAt(0) == t.charAt(0)) {
                if (s.compareTo(t) < 0) {
                    if (i + 1 < m && qa.get(i + 1).charAt(0) - s.charAt(0) == 1) i++;
                    else return false;
                } else {
                    if (j + 1 < n && qb.get(j + 1).charAt(0) - t.charAt(0) == 1) j++;
                    else return true;
                }
            } else if (s.charAt(0) < t.charAt(0)) {
                if (t.charAt(0) - s.charAt(0) == 1) {
                    if (i + 1 < m && qa.get(i + 1).charAt(0) - t.charAt(0) <= 1) i++;
                    else return false;
                } else return true;
            } else {
                while (j < n && qb.get(j).charAt(0) - s.charAt(0) < 0) j++;
                if (j == n || qb.get(j).charAt(0) - s.charAt(0) > 1) return true;
            }
        }
        return i < m;
    }
}