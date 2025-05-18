package LC901_1200;
import java.util.*;
public class LC1079_LetterTilePossibilities {
    /**
     * You have n  tiles, where each tile has one letter tiles[i] printed on it.
     *
     * Return the number of possible non-empty sequences of letters you can make using the letters printed on those
     * tiles.
     *
     * Input: tiles = "AAB"
     * Output: 8
     *
     * Input: tiles = "AAABBC"
     * Output: 188
     *
     * Input: tiles = "V"
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= tiles.length <= 7
     * tiles consists of uppercase English letters.
     * @param tiles
     * @return
     */
    // S1
    // time = O(n * n!), space = O(n)
    int n, res;
    HashMap<Character, Integer> map;
    public int numTilePossibilities(String tiles) {
        map = new HashMap<>();
        for (char c : tiles.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        n = tiles.length();
        dfs(0);
        return res;
    }

    private void dfs(int u) {
        if (u > 0) res++;
        if (u == n) return;

        for (char k : map.keySet()) {
            if (map.getOrDefault(k, 0) > 0) {
                map.put(k, map.get(k) - 1);
                dfs(u + 1);
                map.put(k, map.get(k) + 1);
            }
        }
    }

    // S2
    // time = O(n * n!), space = O(n)
    public int numTilePossibilities2(String tiles) {
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        return dfs(chars, 0) - 1;
    }

    private int dfs(char[] chars, int u) {
        int res = 1;
        if (u == chars.length) return res;

        for (int i = 0; i < chars.length; i++) {
            if (i > 0 && chars[i] == chars[i - 1]) continue;
            if (chars[i] == ' ') continue;
            char c = chars[i];
            chars[i] = ' ';
            res += dfs(chars, u + 1);
            chars[i] = c;
        }
        return res;
    }
}