package LC3601_3900;
import java.util.*;
public class LC3656_DetermineifaSimpleGraphExists {
    /**
     * You are given an integer array degrees, where degrees[i] represents the desired degree of the ith vertex.
     *
     * Your task is to determine if there exists an undirected simple graph with exactly these vertex degrees.
     *
     * A simple graph has no self-loops or parallel edges between the same pair of vertices.
     *
     * Return true if such a graph exists, otherwise return false.
     *
     * Input: degrees = [3,1,2,2]
     * Output: true
     *
     * Input: degrees = [1,3,3,1]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= n == degrees.length <= 10^5
     * 0 <= degrees[i] <= n - 1
     * @param degrees
     * @return
     */
    // S1: Erdős–Gallai theorem (graphical sequence test)
    // time = O(nlogn), space = O(n)
    public boolean simpleGraphExists(int[] degrees) {
        int n = degrees.length;
        Integer[] d = new Integer[n];
        for (int i = 0; i < n; i++) d[i] = degrees[i];
        Arrays.sort(d, (o1, o2) -> o2 - o1);

        long sum = 0;
        for (int x : d) sum += x;
        if (sum % 2 == 1) return false;

        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + d[i - 1];
        for (int i = 1; i <= n; i++) {
            long left = s[i];
            int l = i, r = n;
            while (l < r) {
                int mid = l + r >> 1;
                if (d[mid] <= i) r = mid;
                else l = mid + 1;
            }
            int pos = r;
            long v = 1L * (pos - i) * i;
            v += s[n] - s[pos];
            long right = 1L * i * (i - 1) + v;
            if (left > right) return false;
        }
        return true;
    }
}