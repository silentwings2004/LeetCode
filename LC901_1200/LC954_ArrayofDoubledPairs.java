package LC901_1200;
import com.sun.source.tree.Tree;

import java.util.*;
public class LC954_ArrayofDoubledPairs {
    /**
     * Given an integer array of even length arr, return true if it is possible to reorder arr such that
     * arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.
     *
     * Input: arr = [3,1,3,6]
     * Output: false
     *
     * Constraints:
     *
     * 2 <= arr.length <= 3 * 10^4
     * arr.length is even.
     * -105 <= arr[i] <= 10^5
     * @param arr
     * @return
     */
    // S1: TreeMap
    // time = O(nlogn), space = O(n)
    public boolean canReorderDoubled(int[] arr) {
        // corner case
        if (arr == null || arr.length == 0) return false;

        int n = arr.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : arr) map.put(num, map.getOrDefault(num, 0) + 1);

        for (int key : map.keySet()) {
            if (map.get(key) == 0) continue;
            int val = key < 0 ? key / 2 : key * 2;
            if (key < 0 && key % 2 != 0 || map.get(key) > map.getOrDefault(val, 0)) return false;
            map.put(val, map.get(val) - map.get(key));
        }
        return true;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public boolean canReorderDoubled2(int[] arr) {
        return check(arr, 1) && check(arr, -1);
    }

    private boolean check(int[] arr, int t) {
        for (int i = 0; i < arr.length; i++) arr[i] *= t;
        Arrays.sort(arr);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : arr) {
            if (x > 0) map.put(x, map.getOrDefault(x, 0) + 1);
        }


        for (int x : arr) {
            if (x <= 0) continue;
            int a = x, b = x * 2;

            if (!map.containsKey(a)) continue;
            if (!map.containsKey(b)) return false;
            map.put(a, map.get(a) - 1);
            map.put(b, map.get(b) - 1);
            if (map.get(a) == 0) map.remove(a);
            if (map.get(b) == 0) map.remove(b);
        }
        return true;
    }
}
