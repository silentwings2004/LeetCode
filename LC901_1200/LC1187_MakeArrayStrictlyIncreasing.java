package LC901_1200;
import java.util.*;
public class LC1187_MakeArrayStrictlyIncreasing {
    /**
     * Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make
     * arr1 strictly increasing.
     *
     * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment
     * arr1[i] = arr2[j].
     *
     * If there is no way to make arr1 strictly increasing, return -1.
     *
     * Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
     * Output: 1
     *
     * Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
     * Output: 2
     *
     * Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= arr1.length, arr2.length <= 2000
     * 0 <= arr1[i], arr2[i] <= 10^9
     * @param arr1
     * @param arr2
     * @return
     */
    // time = O(n * m * logm), space = O(n * m)
    final int INF = (int) 2e9;
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int n = arr1.length, m = arr2.length;
        Arrays.sort(arr2);
        int[][] f = new int[n][m  + 1];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], INF);
        f[0][0] = arr1[0];
        for (int i = 1; i <= m; i++) f[0][i] = Math.min(arr1[0], arr2[0]);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (f[i - 1][j] < arr1[i]) f[i][j] = arr1[i];
                if (j > 0) {
                    int k = upper_bound(arr2, f[i - 1][j - 1]);
                    if (k < m) f[i][j] = Math.min(f[i][j], arr2[k]);
                }
            }
        }

        for (int i = 0; i <= m; i++) {
            if (f[n - 1][i] < INF) return i;
        }
        return -1;
    }

    private int upper_bound(int[] a, int t) {
        int l = 0, r = a.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (a[mid] > t) r = mid;
            else l = mid + 1;
        }
        return a[r] > t ? r : r + 1;
    }
}
/**
 * 第2个数组先排序
 * 保序来替换
 * 长度为i的末尾元素最小值
 * dp
 * 状态表示 f(i,j)
 * 集合：所有对前i项操作j次的方案的集合
 * 属性：第i项的最小值
 * 状态计算：
 * 1. 操作ai: f(i-1,j-1)
 * 2. 不操作ai: f(i-1,j) < ai
 * time = O(n^2*logn)
 */