package LC2401_2700;
import java.util.*;
public class LC2418_SortthePeople {
    /**
     * You are given an array of strings names, and an array heights that consists of distinct positive integers. Both
     * arrays are of length n.
     *
     * For each index i, names[i] and heights[i] denote the name and height of the ith person.
     *
     * Return names sorted in descending order by the people's heights.
     *
     * Input: names = ["Mary","John","Emma"], heights = [180,165,170]
     * Output: ["Mary","Emma","John"]
     *
     * Constraints:
     *
     * n == names.length == heights.length
     * 1 <= n <= 10^3
     * 1 <= names[i].length <= 20
     * 1 <= heights[i] <= 10^5
     * names[i] consists of lower and upper case English letters.
     * All the values of heights are distinct.
     * @param names
     * @param heights
     * @return
     */
    // time = O(nlogn), space = O(n)
    public String[] sortPeople(String[] names, int[] heights) {
        HashMap<Integer, String> map = new HashMap<>();
        int n = names.length;
        for (int i = 0; i < n; i++) map.put(heights[i], names[i]);
        Arrays.sort(heights);

        String[] res = new String[n];
        for (int i = 0; i < n; i++) res[i] = map.get(heights[n - 1 - i]);
        return res;
    }
}
