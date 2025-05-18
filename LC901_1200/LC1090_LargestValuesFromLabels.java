package LC901_1200;
import java.util.*;
public class LC1090_LargestValuesFromLabels {
    /**
     * There is a set of n items. You are given two integer arrays values and labels where the value and the label of
     * the ith element are values[i] and labels[i] respectively. You are also given two integers numWanted and useLimit.
     *
     * Choose a subset s of the n elements such that:
     *
     * The size of the subset s is less than or equal to numWanted.
     * There are at most useLimit items with the same label in s.
     * The score of a subset is the sum of the values in the subset.
     *
     * Return the maximum score of a subset s.
     *
     * Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1
     * Output: 9
     *
     * Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2
     * Output: 12
     *
     * Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1
     * Output: 16
     *
     * Constraints:
     *
     * n == values.length == labels.length
     * 1 <= n <= 2 * 10^4
     * 0 <= values[i], labels[i] <= 2 * 10^4
     * 1 <= numWanted, useLimit <= n
     * @param values
     * @param labels
     * @param numWanted
     * @param useLimit
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        int[][] w = new int[n][2];
        for (int i = 0; i < n; i++) w[i] = new int[]{values[i], labels[i]};
        Arrays.sort(w, (o1, o2) -> o2[0] - o1[0]);
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(w[i][1]) || map.get(w[i][1]) < useLimit) {
                res += w[i][0];
                map.put(w[i][1], map.getOrDefault(w[i][1], 0) + 1);
                cnt++;
                if (cnt == numWanted) break;
            }
        }
        return res;
    }
}