package LC1501_1800;
import java.util.*;
public class LC1739_BuildingBoxes {
    /**
     * You have a cubic storeroom where the width, length, and height of the room are all equal to n units.
     * You are asked to place n boxes in this room where each box is a cube of unit side length.
     * There are however some rules to placing the boxes:
     *
     * You can place the boxes anywhere on the floor.
     * If box x is placed on top of the box y, then each side of the four vertical sides of the box y must either be
     * adjacent to another box or to a wall.
     * Given an integer n, return the minimum possible number of boxes touching the floor.
     *
     * Input: n = 3
     * Output: 3
     *
     * Input: n = 10
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(logn * sqrt(n)), space = O(1)
    public int minimumBoxes(int n) {  // O(logn)
        int l = 1, r = (int) 1e9;
        while (l < r) {
            int mid = l + r >> 1;
            if (cal(mid) >= n) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    // 等腰三角形 (1 + d) * d / 2 <= area
    // d^2 < d * (d + 1) <= 2 * area
    // => d < sqrt(2 * area)
    private long cal(int area) {
        int d = (int)Math.sqrt(2 * area);
        while ((1 + d) * d / 2 > area) {
            d--;
        }
        long diff = area - (1 + d) * d / 2;
        long[] a = new long[d];
        for (int i = 0; i < d; i++) {
            a[i] = d - i; // a: 4, 3, 2, 1
        }
        for (int i = 0; i < diff; i++) { // 加上零头
            a[i]++;
        }

        long total = 0, sufsum = 0;
        for (int i = d - 1; i >= 0; i--) { // 从后往前算suffix sum
            sufsum += a[i];
            total += sufsum;
        }
        return total;
    }

    // S2: Math
    // time = O(n), space = O(1)
    public int minimumBoxes2(int n) {
        int sum = 0, k = 1;
        while (sum + k * (k + 1) / 2 <= n) {
            sum += k * (k + 1) / 2;
            k++;
        }

        int res = k * (k - 1) / 2;
        k = 1;
        while (sum < n) {
            sum += k;
            res++;
            k++;
        }
        return res;
    }
}

/**
 * 俯视图：
 *    3  2  1 (1)            4 3 3 2 1          5  4  3  1
 *    2  1 (1)               3 2 2 1            4  3  1
 *    1 (1)            =>    2 1 1         =>   3  1          => "后缀"重复计算
 *   (1)                     1                  1
 *    => 等腰三角形
 *    => 尽量在最底层构建一个最大的等腰三角形 => 尽量朝等腰三角形去发展 => 求底边数字是多少
 *    n => area = 13 => d = 4 + 3 => n'   n'太大 -> 缩小底边 => n' >= n => 典型二分搜值
 *
 *  任意2个相邻格子之间的高度差最大为1
 */
