package LC3001_3300;
import java.util.*;
public class LC3016_MinimumNumberofPushestoTypeWordII {
    /**
     * You are given a string word containing lowercase English letters.
     *
     * Telephone keypads have keys mapped with distinct collections of lowercase English letters, which can be used to
     * form words by pushing them. For example, the key 2 is mapped with ["a","b","c"], we need to push the key one time
     * to type "a", two times to type "b", and three times to type "c" .
     *
     * It is allowed to remap the keys numbered 2 to 9 to distinct collections of letters. The keys can be remapped to
     * any amount of letters, but each letter must be mapped to exactly one key. You need to find the minimum number of
     * times the keys will be pushed to type the string word.
     *
     * Return the minimum number of pushes needed to type word after remapping the keys.
     *
     * An example mapping of letters to keys on a telephone keypad is given below. Note that 1, *, #, and 0 do not map
     * to any letters.
     *
     * Input: word = "abcde"
     * Output: 5
     *
     * Input: word = "xyzxyzxyzxyz"
     * Output: 12
     *
     * Input: word = "aabbccddeeffgghhiiiiii"
     * Output: 24
     *
     * Constraints:
     *
     * 1 <= word.length <= 10^5
     * word consists of lowercase English letters.
     * @param word
     * @return
     */
    // S1
    // time = O(nlogn), space(1)
    public int minimumPushes(String word) {
        int n = word.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            int u = word.charAt(i) - 'a';
            cnt[u]++;
        }
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < 26; i++) q.add(new int[]{cnt[i], i});
        Collections.sort(q, (o1, o2) -> o2[0] - o1[0]);
        HashMap<Integer, Integer> map = new HashMap<>();
        int k = 0;
        for (int[] x : q) {
            int u = x[1], t = k / 8;
            map.put(u, t + 1);
            k++;
        }
        int res = 0;
        for (int i = 0; i < n; i++) res += map.get(word.charAt(i) - 'a');
        return res;
    }

    // S2
    // time = O(nlogn), space(1)
    public int minimumPushes2(String word) {
        int n = word.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            int u = word.charAt(i) - 'a';
            cnt[u]++;
        }
        Arrays.sort(cnt);
        int res = 0;
        for (int i = 25; i >= 0; i--) res += cnt[i] * ((25 - i) / 8 + 1);
        return res;
    }
}