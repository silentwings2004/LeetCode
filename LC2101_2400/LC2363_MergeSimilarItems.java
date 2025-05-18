package LC2101_2400;
import java.util.*;
public class LC2363_MergeSimilarItems {
    /**
     * You are given two 2D integer arrays, items1 and items2, representing two sets of items. Each array items has the
     * following properties:
     *
     * items[i] = [valuei, weighti] where valuei represents the value and weighti represents the weight of the ith item.
     * The value of each item in items is unique.
     * Return a 2D integer array ret where ret[i] = [valuei, weighti], with weighti being the sum of weights of all
     * items with value valuei.
     *
     * Note: ret should be returned in ascending order by value.
     *
     * Input: items1 = [[1,1],[4,5],[3,8]], items2 = [[3,1],[1,5]]
     * Output: [[1,6],[3,9],[4,5]]
     *
     * Constraints:
     *
     * 1 <= items1.length, items2.length <= 1000
     * items1[i].length == items2[i].length == 2
     * 1 <= valuei, weighti <= 1000
     * Each valuei in items1 is unique.
     * Each valuei in items2 is unique.
     * @param items1
     * @param items2
     * @return
     */
    // time = O(nlogn), space = O(n)
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        List<List<Integer>> res = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] x : items1) map.put(x[0], map.getOrDefault(x[0], 0) + x[1]);
        for (int[] x : items2) map.put(x[0], map.getOrDefault(x[0], 0) + x[1]);
        for (int x : map.keySet()) res.add(Arrays.asList(x, map.get(x)));
        return res;
    }
}
