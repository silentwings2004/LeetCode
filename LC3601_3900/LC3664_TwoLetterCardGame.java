package LC3601_3900;
import java.util.*;
public class LC3664_TwoLetterCardGame {
    /**
     * You are given a deck of cards represented by a string array cards, and each card displays two lowercase letters.
     *
     * You are also given a letter x. You play a game with the following rules:
     *
     * Start with 0 points.
     * On each turn, you must find two compatible cards from the deck that both contain the letter x in any position.
     * Remove the pair of cards and earn 1 point.
     * The game ends when you can no longer find a pair of compatible cards.
     * Return the maximum number of points you can gain with optimal play.
     *
     * Two cards are compatible if the strings differ in exactly 1 position.
     *
     * Input: cards = ["aa","ab","ba","ac"], x = "a"
     *
     * Output: 2
     *
     * Input: cards = ["aa","ab","ba"], x = "a"
     * Output: 1
     *
     * Input: cards = ["aa","ab","ba","ac"], x = "b"
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= cards.length <= 10^5
     * cards[i].length == 2
     * Each cards[i] is composed of only lowercase English letters between 'a' and 'j'.
     * x is a lowercase English letter between 'a' and 'j'.
     * @param cards
     * @param x
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int score(String[] cards, char x) {
        HashMap<String, Integer> mp1 = new HashMap<>();
        HashMap<String, Integer> mp2 = new HashMap<>();
        int t = 0, mx1 = 0, mx2 = 0;
        for (String s : cards) {
            if (s.charAt(0) == x && s.charAt(1) == x) t++;
            else if (s.charAt(0) == x) {
                mp1.put(s, mp1.getOrDefault(s, 0) + 1);
                mx1 = Math.max(mx1, mp1.get(s));
            } else if (s.charAt(1) == x) {
                mp2.put(s, mp2.getOrDefault(s, 0) + 1);
                mx2 = Math.max(mx2, mp2.get(s));
            }
        }
        int res = 0;
        for (int i = 0; i <= t; i++) {
            res = Math.max(res, cal(mp1, Math.max(mx1, i), i) + cal(mp2, Math.max(mx2, t - i), t - i));
        }
        return res;
    }

    private int cal(HashMap<String, Integer> map, int mx, int t) {
        for (int v : map.values()) t += v;
        return mx > t - mx ? t - mx : t / 2;
    }
}
/**
 * 给定数组 a，每次操作，删除 a 中的两个不同元素（配对）。最多可以操作多少次？
 * 定理：最多操作 min(n /2, n - m) 次。其中 n 是数组 a 的长度，m 是出现次数最多的元素的出现次数。
 */
