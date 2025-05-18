package LC601_900;
import java.util.*;
public class LC884_UncommonWordsfromTwoSentences {
    /**
     * A sentence is a string of single-space separated words where each word consists only of lowercase letters.
     *
     * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
     *
     * Given two sentences s1 and s2, return a list of all the uncommon words. You may return the answer in any order.
     *
     * Input: s1 = "this apple is sweet", s2 = "this apple is sour"
     * Output: ["sweet","sour"]
     *
     * Constraints:
     *
     * 1 <= s1.length, s2.length <= 200
     * s1 and s2 consist of lowercase English letters and spaces.
     * s1 and s2 do not have leading or trailing spaces.
     * All the words in s1 and s2 are separated by a single space.
     * @param s1
     * @param s2
     * @return
     */
    // S1
    // time = O(m + n), space = O(m + n)
    public String[] uncommonFromSentences(String s1, String s2) {
        String[] arr1 = s1.split(" ");
        String[] arr2 = s2.split(" ");

        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        for (String s : arr1) map1.put(s, map1.getOrDefault(s, 0) + 1);
        for (String s : arr2) map2.put(s, map2.getOrDefault(s, 0) + 1);

        List<String> res = new ArrayList<>();
        for (String key : map1.keySet()) {
            if (map1.get(key) > 1) continue;
            if (map2.getOrDefault(key, 0) == 0) res.add(key);
        }
        for (String key : map2.keySet()) {
            if (map2.get(key) > 1) continue;
            if (map1.getOrDefault(key, 0) == 0) res.add(key);
        }

        String[] ans = new String[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }

    // S2
    // time = O(m + n), space = O(m + n)
    public String[] uncommonFromSentences2(String s1, String s2) {
        HashMap<String, Integer> hm1 = new HashMap<>();
        HashMap<String, Integer> hm2 = new HashMap<>();
        work(s1, hm1);
        work(s2, hm2);

        List<String> res = new ArrayList<>();
        for (String s : hm1.keySet()) {
            if (hm1.get(s) == 1 && !hm2.containsKey(s)) res.add(s);
        }

        for (String s : hm2.keySet()) {
            if (hm2.get(s) == 1 && !hm1.containsKey(s)) res.add(s);
        }
        return res.toArray(new String[res.size()]);
    }

    private void work(String s, HashMap<String, Integer> map) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ' ') continue;
            int j = i + 1;
            while (j < n && s.charAt(j) != ' ') j++;
            String t = s.substring(i, j);
            map.put(t, map.getOrDefault(t, 0) + 1);
            i = j;
        }
    }
}