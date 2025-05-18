package LC901_1200;
import java.util.*;
public class LC914_XofaKindinaDeckofCards {
    /**
     * You are given an integer array deck where deck[i] represents the number written on the ith card.
     *
     * Partition the cards into one or more groups such that:
     *
     * Each group has exactly x cards where x > 1, and
     * All the cards in one group have the same integer written on them.
     * Return true if such partition is possible, or false otherwise.
     *
     * Input: deck = [1,2,3,4,4,3,2,1]
     * Output: true
     *
     * Input: deck = [1,1,1,2,2,2,3,3]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= deck.length <= 10^4
     * 0 <= deck[i] < 10^4
     * @param deck
     * @return
     */
    // time = O(nlogn), space = O(n)
    public boolean hasGroupsSizeX(int[] deck) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : deck) map.put(x, map.getOrDefault(x, 0) + 1);
        int d = 0;
        for (int v : map.values()) d = gcd(d, v);
        return d >= 2;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}