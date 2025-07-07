package LC3601_3900;

public class LC3609_MinimumMovestoReachTargetinGrid {
    /**
     * You are given four integers sx, sy, tx, and ty, representing two points (sx, sy) and (tx, ty) on an infinitely
     * large 2D grid.
     *
     * You start at (sx, sy).
     *
     * At any point (x, y), define m = max(x, y). You can either:
     *
     * Move to (x + m, y), or
     * Move to (x, y + m).
     * Return the minimum number of moves required to reach (tx, ty). If it is impossible to reach the target, return -1.
     *
     * Input: sx = 1, sy = 2, tx = 5, ty = 4
     * Output: 2
     *
     * Input: sx = 0, sy = 1, tx = 2, ty = 3
     * Output: 3
     *
     * Input: sx = 1, sy = 1, tx = 2, ty = 2
     * Output: -1
     *
     * Constraints:
     *
     * 0 <= sx <= tx <= 10^9
     * 0 <= sy <= ty <= 10^9
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     */
    // time = O(log(max(x,y)), space = O(1)
    public int minMoves(int sx, int sy, int tx, int ty) {
        int res = 0;
        while (tx != sx || ty != sy) {
            if (tx < sx || ty < sy) return -1;
            if (tx == ty) {
                if (sy > 0) tx = 0;
                else ty = 0;
                res++;
                continue;
            }
            if (tx < ty) {
                int t = tx;
                tx = ty;
                ty = t;
                t = sx;
                sx = sy;
                sy = t;
            }
            if (tx > ty * 2) {
                if (tx % 2 == 1) return -1;
                tx /= 2;
            } else tx -= ty;
            res++;
        }
        return res;
    }
}
/**
 * 分类讨论：
 * 起点到终点的路径，如果有，那么恰好有一条
 * 起点开始想？不好想，走法太多了
 * 终点开始倒着想？倒着走，走法居然是唯一的！
 *
 * 1. (x,y) 倒着回到起点 (sx, sy)
 * 2.上一部在哪？
 * 为了简化后面的流程，假设 x >= y 且 x > 0，如果不满足，那么 swap(x,y) swap(sx,sy)
 * 1. 上一步执行了操作1：(x'+ m,y) -> (x,y)
 *  1.1 (x/2, y) -> (x,y)  前提 x 是偶数且 x >= 2y
 *      m = max(x',y), if x' >= y => m = x'
 *      => (x'+m,y) = (2*x', y) -> (x,y)  => x' = x / 2
 *  1.2 （x-y,y) -> (x,y)  前提: x <= 2y
 *       (x'+y,y) -> (x, y)
 *      x' = x - y
 * 2. 上一步执行了操作2：(x,y'+m) -> (x,y), m = max(x,y')
 *  2.1  (x, y'*2) -> (x,y)   前提: x <= y / 2 且 y 是偶数，但前提是 x >= y => y <= x <= y / 2 => y <= y / 2 =>
 *      y <= 0 => y = 0 => x = 0 && y = 0, 但这与假设不符，所以不考虑这种情况
 *      x <= y' => (x,y'*2) -> (x,y) => y' = y / 2
 *  2.2 (x,y-x) -> (x,y) 前提: y - x >= 0 => y >= x, but x >= y => x = y，且坐标不能是负数，相当于从(x,0) -> (x,x)
 *      x >= y / 2
 *  (5,0) -> (5,5)
 *  (0,5) -> (5,5)
 *
 *  根据以上结论，分类讨论
 *  1. if x = y,
 *     1.1 if sx > 0 -> 只能把 y = 0
 *     1.2 if sy > 0 -> 只能把 x = 0
 *     1.3 if sx = sy = 0? -> 起点动不了, 所以无法到达终点
 *  2. if x > y, 只能做操作1 不等号的三歧性
 *      2.1 x > 2y -> 把 x 减半 (前提是 x 是偶数)
 *      2.2 x < 2y -> 把 x 减去 y
 *      2.3 x = 2y -> 把 x 减半得到 y，把 x 减去 y 也得到 y, 所以是一样的，可以整合到 2.2 中
 * 操作居然是唯一的 => 起点与终点之间的路是唯一的
 * 所以只需要【模拟】上述过程即可，没有任何的贪心想法在里面，纯模拟
 *
 *
 */