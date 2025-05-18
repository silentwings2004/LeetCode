package LC1201_1500;
import java.util.*;
public class LC1331_RankTransformofanArray {
    /**
     * Given an array of integers arr, replace each element with its rank.
     *
     * The rank represents how large the element is. The rank has the following rules:
     *
     * Rank is an integer starting from 1.
     * The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
     * Rank should be as small as possible.
     *
     * Input: arr = [40,10,20,30]
     * Output: [4,1,2,3]
     *
     * Constraints:
     *
     * 0 <= arr.length <= 10^5
     * -10^9 <= arr[i] <= 10^9
     * @param arr
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] arrayRankTransform(int[] arr) {
        int[] t = arr.clone();
        Arrays.sort(t);
        HashMap<Integer, Integer> map = new HashMap<>();
        int idx = 0, n = arr.length;
        for (int x : t) {
            if (!map.containsKey(x)) map.put(x, ++idx);
            else map.put(x, idx);
        }
        for (int i = 0; i < n; i++) arr[i] = map.get(arr[i]);
        return arr;
    }
}
