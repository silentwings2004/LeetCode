package LC3901_4200;
import java.util.*;
public class LC3913_SortVowelsbyFrequency {
    /**
     * You are given a string s consisting of lowercase English characters.
     *
     * Rearrange only the vowels in the string so that they appear in non-increasing order of their frequency.
     *
     * If multiple vowels have the same frequency, order them by the position of their first occurrence in s.
     *
     * Return the modified string.
     *
     * Vowels are 'a', 'e', 'i', 'o', and 'u'.
     *
     * The frequency of a letter is the number of times it occurs in the string.
     *
     * Input: s = "leetcode"
     * Output: "leetcedo"
     *
     * Input: s = "aeiaaioooa"
     * Output: "aaaaoooiie"
     *
     * Input: s = "baeiou"
     * Output: "baeiou"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of lowercase English letters
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String sortVowels(String s) {
        String vowel = "aeiou";
        int n = s.length();
        int[] cnt = new int[5], pos = new int[5];
        Arrays.fill(pos, -1);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int u = vowel.indexOf(c);
            if (u == -1) continue;
            if (pos[u] == -1) pos[u] = i;
            cnt[u]++;
        }
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (pos[i] == -1) continue;
            q.add(new int[]{i, cnt[i], pos[i]});
        }
        Collections.sort(q, (o1, o2) -> o1[1] != o2[1] ? o2[1] - o1[1] : o1[2] - o2[2]);
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < n; i++) {
            char c = s.charAt(i);
            int u = vowel.indexOf(c);
            if (u == -1) sb.append(c);
            else {
                sb.append(vowel.charAt(q.get(j)[0]));
                q.get(j)[1]--;
                if (q.get(j)[1] == 0) j++;
            }
        }
        return sb.toString();
    }
}