package LC2401_2700;
import java.util.*;
public class LC2423_RemoveLetterToEqualizeFrequency {
    /**
     * ou are given a 0-indexed string word, consisting of lowercase English letters. You need to select one index and
     * remove the letter at that index from word so that the frequency of every letter present in word is equal.
     *
     * Return true if it is possible to remove one letter so that the frequency of all letters in word are equal, and
     * false otherwise.
     *
     * Note:
     *
     * The frequency of a letter x is the number of times it occurs in the string.
     * You must remove exactly one letter and cannot chose to do nothing.
     *
     * Input: word = "abcc"
     * Output: true
     *
     * Input: word = "aazz"
     * Output: false
     *
     * Constraints:
     *
     * 2 <= word.length <= 100
     * word consists of lowercase English letters only.
     * @param word
     * @return
     */
    // time = O(n^2), space = O(1)
    public boolean equalFrequency(String word) {
        int n = word.length();
        for (int i = 0; i < n; i++) {
            if (check(word, i)) return true;
        }
        return false;
    }

    private boolean check(String s, int idx) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            if (i == idx) continue;
            char c = s.charAt(i);
            cnt[c - 'a']++;
        }

        int freq = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                if (freq == 0) freq = cnt[i];
                else if (freq != cnt[i]) return false;
            }
        }
        return true;
    }

    // S2
    // time = O(n), space = O(n)
    public boolean equalFrequency2(String word) {
        HashMap<Character, Integer> map = new HashMap<>(); // {val, cnt}
        HashMap<Integer, Integer> cnt = new HashMap<>(); // {cnt, amount}

        for (char c : word.toCharArray()) {
            if (map.containsKey(c)) {
                int f = map.get(c);
                cnt.put(f, cnt.get(f) - 1);
                if (cnt.get(f) == 0) cnt.remove(f);
            }
            map.put(c, map.getOrDefault(c, 0) + 1);
            int f = map.get(c);
            cnt.put(f, cnt.getOrDefault(f, 0) + 1);
        }

        if (cnt.size() == 1) {
            for (int key : cnt.keySet()) {
                if (key == 1 || cnt.get(key) == 1) return true;
            }
        } else if (cnt.size() == 2) {
            int[][] tmp = new int[2][2];
            int k = 0;
            for (int key : cnt.keySet()) tmp[k++] = new int[]{key, cnt.get(key)};
            if (tmp[0][0] > tmp[1][0]) {
                int[] t = tmp[0];
                tmp[0] = tmp[1];
                tmp[1] = t;
            }
            if (tmp[0][0] == 1 && tmp[0][1] == 1) return true;
            if (tmp[1][0] == tmp[0][0] + 1 && tmp[1][1] == 1) return true;
        }
        return false;
    }
}