package LC3301_3600;
import java.util.*;
public class LC3382_MaximumAreaRectangleWithPointConstraintsII {
    /**
     * There are n points on an infinite plane. You are given two integer arrays xCoord and yCoord where
     * (xCoord[i], yCoord[i]) represents the coordinates of the ith point.
     *
     * Your task is to find the maximum area of a rectangle that:
     *
     * Can be formed using four of these points as its corners.
     * Does not contain any other point inside or on its border.
     * Has its edges parallel to the axes.
     * Return the maximum area that you can obtain or -1 if no such rectangle is possible.
     *
     * Input: xCoord = [1,1,3,3], yCoord = [1,3,1,3]
     * Output: 4
     *
     * Input: xCoord = [1,1,3,3,2], yCoord = [1,3,1,3,2]
     * Output: -1
     *
     * Input: xCoord = [1,1,3,3,1,3], yCoord = [1,3,1,3,2,2]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= xCoord.length == yCoord.length <= 2 * 10^5
     * 0 <= xCoord[i], yCoord[i] <= 8 * 10^7
     * All the given points are unique.
     * @param xCoord
     * @param yCoord
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long maxRectangleArea(int[] xCoord, int[] yCoord) {
        int n = xCoord.length;
        int[] ys = disc(yCoord);
        int[][] p = new int[n][2];
        for (int i = 0; i < n; i++) {
            p[i] = new int[]{xCoord[i], find(ys, yCoord[i])};
        }
        Arrays.sort(p, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        HashMap<Long, Integer> hx = new HashMap<>();
        HashMap<Long, Integer> hy = new HashMap<>();
        Fenwick fen = new Fenwick(n);

        long res = -1;
        for (int i = 0; i < n; i++) {
            fen.add(p[i][1], 1);
            if (i > 0 && p[i][0] == p[i - 1][0]) {
                long yc = 1L * p[i][1] << 32 | p[i - 1][1];
                int rs = fen.rangeSum(p[i - 1][1], p[i][1] + 1); // [y1 - 1, y2]
                if (hy.containsKey(yc)) {
                    int last = hy.get(yc);
                    if (rs - last == 2) {
                        int x = hx.get(yc);
                        long area = 1L * (p[i][0] - x) * (ys[p[i][1]] - ys[p[i - 1][1]]);
                        res = Math.max(res, area);
                    }
                }
                hy.put(yc, rs);
                hx.put(yc, p[i][0]);
            }
        }
        return res;
    }

    private int find(int[] nums, int t) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= t) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private int[] disc(int[] y) {
        List<Integer> q = new ArrayList<>();
        for (int x : y) q.add(x);
        q = new ArrayList<>(new HashSet<>(q));
        Collections.sort(q);
        int[] ys = new int[q.size()];
        for (int i = 0; i < q.size(); i++) ys[i] = q.get(i);
        return ys;
    }

    class Fenwick {
        private int n;
        private int[] a;
        public Fenwick(int n) {
            this.n = n;
            this.a = new int[n + 1];
        }

        private void add(int x, int v) {
            for (int i = x + 1; i <= n; i += i & -i) {
                a[i - 1] = a[i - 1] + v;
            }
        }

        private int sum(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= i & -i) {
                ans = ans + a[i - 1];
            }
            return ans;
        }

        private int rangeSum(int l, int r) {
            return sum(r) - sum(l);
        }
    }
}
/**
 * O(n^5) -> O(nlogn)
 * 枚举矩形的其中一个点(右上角)  (x2, y2)
 * 左上角: (x1, y2)
 * 右下角: (x2, y1)
 * => 左下角: (x1, y1)
 * 离线版本的二维数点问题
 * 计算 [x1, x2] x [y1, y2] 这个矩形区域内的点的个数
 * 本题是只有询问，没有更新的二维数点问题
 * 刚才说的矩形，我们值保证了边界上没有其他点，但是区域内的点的个数还不知道
 * 给你一堆询问 queries[i] = [x1, x2, y1, y2] 区域内
 * 离线算法
 * 每个询问拆成两个(前缀和的思想)
 * 计算 <= x1 - 1 和 y [y1,y2] 中点的个数
 * 按照 x 去把这 2q 个拆开后的询问分组
 * 从小到大遍历 x, 一边更新树状数组，一边回答询问
 */