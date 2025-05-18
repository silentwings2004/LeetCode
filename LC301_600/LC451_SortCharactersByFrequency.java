package LC301_600;
import java.util.*;
public class LC451_SortCharactersByFrequency {
    /**
     * Given a string s, sort it in decreasing order based on the frequency of characters, and return the sorted string.
     *
     * Input: s = "tree"
     * Output: "eert"
     *
     * Constraints:
     *
     * 1 <= s.length <= 5 * 10^5
     * s consists of English letters and digits.
     * @param s
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public String frequencySort(String s) {
        int[] cnt = new int[128];
        for (char c : s.toCharArray()) cnt[c]++;

        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            if (cnt[i] > 0) q.add(new int[]{cnt[i], i});
        }
        Collections.sort(q, (o1, o2) -> o2[0] - o1[0]);
        StringBuilder sb = new StringBuilder();
        for (int[] x : q) {
            int f = x[0], c = x[1];
            for (int i = 0; i < f; i++) sb.append((char)(c));
        }
        return sb.toString();
    }

    // S2
    // time = O(nlogn), space = O(n)
    public String frequencySort2(String s) {
        int[] cnt = new int[128];
        char[] chars = s.toCharArray();
        int n = chars.length;
        Character[] cs = new Character[n];
        for (int i = 0; i < n; i++) cs[i] = chars[i];
        for (char c : cs) cnt[c]++;
        Arrays.sort(cs, (o1, o2) -> cnt[o1] != cnt[o2] ? cnt[o2] - cnt[o1] : o1 - o2);
        for (int i = 0; i < n; i++) chars[i] = cs[i];
        return String.valueOf(chars);
    }

    // S3
    // time = O(n), space = O(n)
    public String frequencySort3(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray()) count[c]++;

        int n = s.length();
        List<Character>[] bucket = new List[n + 1];
        for (int i = 0; i < 128; i++) {
            if (count[i] > 0) {
                if (bucket[count[i]] == null) {
                    bucket[count[i]] = new ArrayList<>();
                }
                bucket[count[i]].add((char)(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 0; i--) {
            if (bucket[i] != null) {
                for (char c : bucket[i]) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }

}