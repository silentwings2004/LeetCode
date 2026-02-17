package LC3601_3900;
import java.util.*;
public class LC3842_ToggleLightBulbs {
    /**
     * You are given an array bulbs of integers between 1 and 100.
     *
     * There are 100 light bulbs numbered from 1 to 100. All of them are switched off initially.
     *
     * For each element bulbs[i] in the array bulbs:
     *
     * If the bulbs[i]th light bulb is currently off, switch it on.
     * Otherwise, switch it off.
     * Return the list of integers denoting the light bulbs that are on in the end, sorted in ascending order. If no
     * bulb is on, return an empty list.
     *
     * Input: bulbs = [10,30,20,10]
     * Output: [20,30]
     *
     * Input: bulbs = [100,100]
     * Output: []
     *
     * Constraints:
     *
     * 1 <= bulbs.length <= 100
     * 1 <= bulbs[i] <= 100
     * @param bulbs
     * @return
     */
    // time = O(n), space = O(1)
    public List<Integer> toggleLightBulbs(List<Integer> bulbs) {
        List<Integer> res = new ArrayList<>();
        int[] b = new int[101];
        for (int x : bulbs) b[x] ^= 1;
        for (int i = 1; i <= 100; i++) {
            if (b[i] == 1) res.add(i);
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public List<Integer> toggleLightBulbs2(List<Integer> bulbs) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : bulbs) map.put(x, map.getOrDefault(x, 0) + 1);
        List<Integer> res = new ArrayList<>();
        for (int k : map.keySet()) {
            int v = map.get(k);
            if (v % 2 == 1) res.add(k);
        }
        Collections.sort(res);
        return res;
    }
}