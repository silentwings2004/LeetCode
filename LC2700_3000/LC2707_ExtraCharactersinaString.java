package LC2700_3000;
import java.util.*;
public class LC2707_ExtraCharactersinaString {
    /**
     * You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more
     * non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters
     * in s which are not present in any of the substrings.
     *
     * Return the minimum number of extra characters left over if you break up s optimally.
     *
     * Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
     * Output: 1
     *
     * Input: s = "sayhelloworld", dictionary = ["hello","world"]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= s.length <= 50
     * 1 <= dictionary.length <= 50
     * 1 <= dictionary[i].length <= 50
     * dictionary[i] and s consists of only lowercase English letters
     * dictionary contains distinct words
     * @param s
     * @param dictionary
     * @return
     */
    // S1: dfs
    // time = O(mlogm + n*m), space = O(n)
    int res;
    HashMap<Character, List<String>> map;
    public int minExtraChar(String s, String[] dictionary) {
        map = new HashMap<>();
        int n = s.length();
        res = n;
        Arrays.sort(dictionary, (o1, o2) -> o1.length() - o2.length());
        for (String str : dictionary) {
            char c = str.charAt(0);
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(str);
        }
        dfs(s, 0, 0, dictionary);
        return res;
    }

    private void dfs(String s, int u, int sum, String[] dict) {
        if (u == s.length()) {
            res = Math.min(res, s.length() - sum);
            return;
        }
        if (s.length() - (s.length() - u + sum) >= res) return;

        char c = s.charAt(u);
        for (String t : map.getOrDefault(c, new ArrayList<>())) {
            if (u + t.length() > s.length()) break;
            if (s.substring(u, u + t.length()).equals(t)) {
                dfs(s, u + t.length(), sum + t.length(), dict);
            }
        }
        dfs(s, u + 1, sum, dict);
    }

    // S2: dp
    // time = O(n^3 + L), space = O(n + L)
    public int minExtraChar2(String s, String[] dictionary) {
        HashSet<String> set = new HashSet<>();
        for (String str : dictionary) set.add(str);
        int n = s.length();
        int[] f = new int[n + 1];

        for (int i = 0; i < n; i++) {
            f[i + 1] = f[i] + 1; // not pick
            for (int j = 0; j <= i; j++) {
                if (set.contains(s.substring(j, i + 1))) {
                    f[i + 1] = Math.min(f[i + 1], f[j]);
                }
            }
        }
        return f[n];
    }
}