package LC2101_2400;
import java.util.*;
public class LC2282_NumberofPeopleThatCanBeSeeninaGrid {
    /**
     * You are given an m x n 0-indexed 2D array of positive integers heights where heights[i][j] is the height of the
     * person standing at position (i, j).
     *
     * A person standing at position (row1, col1) can see a person standing at position (row2, col2) if:
     *
     * The person at (row2, col2) is to the right or below the person at (row1, col1). More formally, this means that
     * either row1 == row2 and col1 < col2 or row1 < row2 and col1 == col2.
     * Everyone in between them is shorter than both of them.
     * Return an m x n 2D array of integers answer where answer[i][j] is the number of people that the person at
     * position (i, j) can see.
     *
     * Input: heights = [[3,1,4,2,5]]
     * Output: [[2,1,2,1,0]]
     *
     * Input: heights = [[5,1],[3,1],[4,1]]
     * Output: [[3,1],[2,1],[1,0]]
     *
     * Constraints:
     *
     * 1 <= heights.length <= 400
     * 1 <= heights[i].length <= 400
     * 1 <= heights[i][j] <= 10^5
     * @param heights
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int[][] seePeople(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            Stack<Integer> stk = new Stack<>();
            for (int j = 0; j < n; j++) {
                int lastRemoved = -1;
                while (!stk.isEmpty() && heights[i][j] >= heights[i][stk.peek()]) {
                    lastRemoved = heights[i][stk.peek()];
                    res[i][stk.pop()]++;
                }
                if (!stk.isEmpty() && heights[i][j] != lastRemoved) res[i][stk.peek()]++;
                stk.push(j);
            }
        }

        for (int j = 0; j < n; j++) {
            Stack<Integer> stk = new Stack<>();
            for (int i = 0; i < m; i++) {
                int lastRemoved = -1;
                while (!stk.isEmpty() && heights[i][j] >= heights[stk.peek()][j]) {
                    lastRemoved = heights[stk.peek()][j];
                    res[stk.pop()][j]++;
                }
                if (!stk.isEmpty() && heights[i][j] != lastRemoved) res[stk.peek()][j]++;
                stk.push(i);
            }
        }
        return res;
    }
}
/**
 * ref: LC1944
 * 单调栈
 * 允许相同元素 => 3,1,1。第二个1会把第一个1弹出再入栈，但是注意3虽然大于第二个1，可它是看不到第二个1的。
 * 如果新元素nums[i]如果从栈顶刚弹出了与自己相同的元素，那么它就不能再被此时栈顶的大元素的计数器所加1（虽然大于nums[i]).
 */