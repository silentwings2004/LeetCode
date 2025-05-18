package LC2101_2400;
import java.util.*;
public class LC2307_CheckforContradictionsinEquations {
    /**
     * You are given a 2D array of strings equations and an array of real numbers values, where equations[i] = [Ai, Bi]
     * and values[i] means that Ai / Bi = values[i].
     *
     * Determine if there exists a contradiction in the equations. Return true if there is a contradiction, or false
     * otherwise.
     *
     * Note: Two floating point numbers are considered equal if their absolute difference is less than 10^-5.
     *
     * Input: equations = [["a","b"],["b","c"],["a","c"]], values = [3,0.5,1.5]
     * Output: false
     *
     * Input: equations = [["le","et"],["le","code"],["code","et"]], values = [2,5,0.5]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= equations.length <= 500
     * equations[i].length == 2
     * 1 <= Ai.length, Bi.length <= 5
     * Ai, Bi consist of lower case English letters.
     * equations.length == values.length
     * 0.0 < values[i] <= 20.0
     * @param equations
     * @param values
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int N = 210;
    final double eps = 1e-5;
    int[] p;
    double[] d;
    int idx;
    HashMap<String, Integer> map;
    public boolean checkContradictions(List<List<String>> equations, double[] values) {
        map = new HashMap<>();
        p = new int[N];
        d = new double[N];
        idx = 0;
        for (int i = 0; i < N; i++) {
            p[i] = i;
            d[i] = 1;
        }
        int n = equations.size();
        for (int i = 0; i < n; i++) {
            String s = equations.get(i).get(0), t = equations.get(i).get(1);
            double c = values[i];
            if (!map.containsKey(s)) map.put(s, idx++);
            if (!map.containsKey(t)) map.put(t, idx++);
            int a = map.get(s), b = map.get(t);
            int pa = find(a), pb = find(b);
            if (pa != pb) {
                p[pa] = pb;
                d[pa] = c * d[b] / d[a];
            } else if (Math.abs(d[a] / d[b] - c) > eps) return true;
        }
        return false;
    }

    private int find(int x) {
        if (x != p[x]) {
            int root = find(p[x]);
            d[x] *= d[p[x]];
            p[x] = root;
        }
        return p[x];
    }
}