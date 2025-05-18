package LC001_300;
import java.util.*;
public class LC118_PascalsTriangle {
    /**
     * Given an integer numRows, return the first numRows of Pascal's triangle.
     *
     * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
     *
     * Input: numRows = 5
     * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     *
     * Constraints:
     *
     * 1 <= numRows <= 30
     * @param numRows
     * @return
     */
    // S1
    // time = O(n^2), space = O(n)
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        int n = numRows;
        for (int i = 0; i < n; i++) {
            List<Integer> path = new ArrayList<>();
            path.add(1);
            for (int j = 1; j < i; j++) path.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            if (i > 0) path.add(1);
            res.add(path);
        }
        return res;
    }

    // S2
    // time = O(n^2), space = O(n)
    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> f = new ArrayList<>();
        f.add(Arrays.asList(1));
        int n = numRows;
        for (int i = 1; i < n; i++) {
            f.add(new ArrayList<>());
            for (int j = 0; j <= i; j++) {
                int t = 0;
                if (j <= i - 1) t += f.get(i - 1).get(j);
                if (j - 1 >= 0) t += f.get(i - 1).get(j - 1);
                f.get(i).add(t);
            }
        }
        return f;
    }
}