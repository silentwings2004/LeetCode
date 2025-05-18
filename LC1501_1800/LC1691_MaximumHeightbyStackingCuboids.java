package LC1501_1800;
import java.util.*;
public class LC1691_MaximumHeightbyStackingCuboids {
    /**
     * Given n cuboids where the dimensions of the ith cuboid is cuboids[i] = [widthi, lengthi, heighti] (0-indexed).
     * Choose a subset of cuboids and place them on each other.
     *
     * You can place cuboid i on cuboid j if widthi <= widthj and lengthi <= lengthj and heighti <= heightj. You can
     * rearrange any cuboid's dimensions by rotating it to put it on another cuboid.
     *
     * Return the maximum height of the stacked cuboids.
     *
     * Input: cuboids = [[50,45,20],[95,37,53],[45,23,12]]
     * Output: 190
     *
     * Input: cuboids = [[38,25,45],[76,35,3]]
     * Output: 76
     *
     * Input: cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
     * Output: 102
     *
     * Constraints:
     *
     * n == cuboids.length
     * 1 <= n <= 100
     * 1 <= widthi, lengthi, heighti <= 100
     * @param cuboids
     * @return
     */
    // time = O(n^2), space = O(n)
    public int maxHeight(int[][] cuboids) {
        for (int[] x : cuboids) Arrays.sort(x);
        Arrays.sort(cuboids, (o1, o2) -> {
           if (o1[0] != o2[0]) return o2[0] - o1[0];
           if (o1[1] != o2[1]) return o2[1] - o1[1];
           return o2[2] - o1[2];
        });

        int n = cuboids.length;
        int[] f = new int[n];

        int res = 0;
        for (int i = 0; i < n; i++) {
            f[i] = cuboids[i][2];
            for (int j = 0; j < i; j++) {
                if (cuboids[j][0] >= cuboids[i][0] && cuboids[j][1] >= cuboids[i][1] && cuboids[j][2] >= cuboids[i][2]) {
                    f[i] = Math.max(f[i], f[j] + cuboids[i][2]);
                }
            }
            res = Math.max(res, f[i]);
        }
        return res;
    }
}
/**
 * 对于每一维来说，都是单调递减的
 * 想一下最优解满足什么性质
 * 最优解满足什么性质
 * a1 >= a2
 * b1 >= b2
 * c1 >= c2
 * 任意给一组解，都可以转化成以上的关系 => 可以任意调换
 * 对每一列，从下往上递减
 * 对每一行，c >= b >= a
 * total height = sum{ci}
 * 理论最大值 = sum{ci}
 * 把c转成高 => 子集中必有最优解
 * (1) 合法
 * (2) ai <= bi <= ci
 * (3) 高为ci
 * 加了这2个条件就可以做了
 * ai > aj => ai is down, aj is up, same for bi, bj and ci, cj.
 * 选出一个子序列，三维坐标分别递减，并且总高度最大
 * => 单调下降子序列问题
 * 暴力dp即可
 * f[i] = f[j] + ci
 * ref: AC734 能量石
 * 在满足所有性质的解里求最优解即可
 */