package LC3901_4200;

public class LC4103_RemoveElementsbyTypeandColor {
    /**
     * You are given three integer arrays types, colors, and groupSize, where types and colors have length n, and
     * groupSize has length m.
     *
     * types[i] represents the type of the element at index i.
     * colors[i] represents the color of the element at index i.
     * groupSize[t] represents the required number of elements in a removable group of type t.
     * In one operation, you may remove exactly groupSize[t] consecutive elements if all of them have type t and the
     * same color.
     *
     * After each removal, the remaining elements become adjacent again while maintaining their relative order.
     *
     * Return true if it is possible to remove all elements using a sequence of valid operations. Otherwise, return
     * false.
     *
     * Input: types = [0,1,1,0], colors = [5,3,3,5] , groupSize = [2,2]
     * Output: true
     *
     * Input: types = [0,1,0], colors = [9,9,9], groupSize = [3,2]
     * Output: false
     *
     * Input: types = [2,0,1,2], colors = [7,5,4,7], groupSize = [3,1,2]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= n == types.length == colors.length <= 10^5
     * 1 <= m == groupSize.length <= 10^5
     * 0 <= types[i] <= m - 1
     * 1 <= colors[i] <= 10^5
     * 1 <= groupSize[t] <= n
     * @param types
     * @param colors
     * @param groupSize
     * @return
     */
    // time = O(n), space = O(n)
    public boolean canRemoveAll(int[] types, int[] colors, int[] groupSize) {
        int n = types.length;
        int[][] stk = new int[n + 1][3]; // {type, color, cnt}
        int tt = 0;
        for (int i = 0; i < n; i++) {
            if (tt > 0 && stk[tt][0] == types[i] && stk[tt][1] == colors[i]) stk[tt][2]++;
            else stk[++tt] = new int[]{types[i], colors[i], 1};
            if (stk[tt][2] == groupSize[types[i]]) tt--;
        }
        return tt == 0;
    }
}