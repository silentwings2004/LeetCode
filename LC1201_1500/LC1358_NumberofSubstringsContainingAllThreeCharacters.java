package LC1201_1500;
import java.util.*;
public class LC1358_NumberofSubstringsContainingAllThreeCharacters {
    /**
     * Given a string s consisting only of characters a, b and c.
     *
     * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
     *
     * Input: s = "abcabc"
     * Output: 10
     *
     * Input: s = "aaacb"
     * Output: 3
     *
     * Input: s = "abc"
     * Output: 1
     *
     * Constraints:
     *
     * 3 <= s.length <= 5 x 10^4
     * s only consists of a, b or c characters.
     * @param s
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int numberOfSubstrings(String s) {
        TreeSet<Integer>[] sets = new TreeSet[3];
        for (int i = 0; i < 3; i++) sets[i] = new TreeSet<>();
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) sets[s.charAt(i) - 'a'].add(i);
        for (int i = 0; i < n; i++) {
            int pos = i;
            for (int j = 0; j < 3; j++) {
                Integer ck = sets[j].ceiling(i);
                if (ck == null) {
                    pos = -1;
                    break;
                } else pos = Math.max(pos, ck);
            }
            if (pos != -1) res += n - pos;
        }
        return res;
    }

    // S2: two pointers
    // time = O(n), space = O(1)
    public int numberOfSubstrings2(String s) {
        int n = s.length(), res = 0;
        int[] cnt = new int[3];
        for (int i = 0, j = 0; i < n;) {
            while (j < n && !(cnt[0] >= 1 && cnt[1] >= 1 && cnt[2] >= 1)) cnt[s.charAt(j++) - 'a']++;
            if (cnt[0] >= 1 && cnt[1] >= 1 && cnt[2] >= 1) res += n - (j - 1);
            cnt[s.charAt(i++) - 'a']--;
        }
        return res;
    }
}