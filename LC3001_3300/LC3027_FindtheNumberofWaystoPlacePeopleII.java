package LC3001_3300;
import java.util.*;
public class LC3027_FindtheNumberofWaystoPlacePeopleII {
    /**
     * You are given a 2D array points of size n x 2 representing integer coordinates of some points on a 2D-plane,
     * where points[i] = [xi, yi].
     *
     * We define the right direction as positive x-axis (increasing x-coordinate) and the left direction as negative
     * x-axis (decreasing x-coordinate). Similarly, we define the up direction as positive y-axis (increasing
     * y-coordinate) and the down direction as negative y-axis (decreasing y-coordinate)
     *
     * You have to place n people, including Chisato and Takina, at these points such that there is exactly one person
     * at every point. Chisato wants to be alone with Takina, so Chisato will build a rectangular fence with Chisato's
     * position as the upper left corner and Takina's position as the lower right corner of the fence (Note that the
     * fence might not enclose any area, i.e. it can be a line). If any person other than Chisato and Takina is either
     * inside the fence or on the fence, Chisato will be sad.
     *
     * Return the number of pairs of points where you can place Chisato and Takina, such that Chisato does not become
     * sad on building the fence.
     *
     * Note that Chisato can only build a fence with Chisato's position as the upper left corner, and Takina's position
     * as the lower right corner. For example, Chisato cannot build either of the fences in the picture below with four
     * corners (1, 1), (1, 3), (3, 1), and (3, 3), because:
     *
     * With Chisato at (3, 3) and Takina at (1, 1), Chisato's position is not the upper left corner and Takina's
     * position is not the lower right corner of the fence.
     * With Chisato at (1, 3) and Takina at (1, 1), Takina's position is not the lower right corner of the fence.
     *
     * Input: points = [[1,1],[2,2],[3,3]]
     * Output: 0
     *
     * Input: points = [[6,2],[4,4],[2,6]]
     * Output: 2
     *
     * Input: points = [[3,1],[1,3],[1,1]]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= n <= 1000
     * points[i].length == 2
     * -10^9 <= points[i][0], points[i][1] <= 10^9
     * All points[i] are distinct.
     * @param points
     * @return
     */
    // S1: Fenwick
    // time = O(nlogn), space = O(n)
    int[] tr;
    List<Integer> q;
    int m;
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        q = new ArrayList<>();
        for (int i = 0; i < n; i++) q.add(points[i][1]);
        q = new ArrayList<>(new HashSet<>(q));
        Collections.sort(q);

        m = q.size();
        Arrays.sort(points, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        int res = 0;
        for (int i = 0; i < n; i++) {
            tr = new int[m + 1];
            int a = find(points[i][1]) + 1;
            add(a, 1);
            for (int j = i + 1; j < n; j++) {
                int b = find(points[j][1]) + 1;
                add(b, 1);
                if (points[i][0] <= points[j][0] && points[i][1] >= points[j][1]) {
                    int t = rangeSum(b, a);
                    if (t == 2) res++;
                }
            }
        }
        return res;
    }

    private int find(int x) {
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (q.get(mid) >= x) r = mid;
            else l = mid + 1;
        }
        return q.get(r) >= x ? r : r + 1;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, int c) {
        for (int i = x; i <= m; i += lowbit(i)) tr[i] += c;
    }

    private int sum(int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res += tr[i];
        return res;
    }

    private int rangeSum(int l, int r) {
        return sum(r) - sum(l - 1);
    }

    // S2: 二维偏序
    // time = O(n^2), space = O(logn)
    public int numberOfPairs2(int[][] points) {
        Arrays.sort(points, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        int n = points.length, res = 0, inf = 0x3f3f3f3f;
        for (int i = 0; i < n; i++) {
            int max_y = -inf;
            for (int j = i + 1; j < n; j++) {
                if (max_y < points[j][1] && points[j][1] <= points[i][1]) {
                    max_y = points[j][1];
                    res++;
                }
            }
        }
        return res;
    }
}
/**
 * 枚举左上角的点 p, 和右下角的点 q
 * 检查 p 是否在 q 的左上方向
 * 矩形内及边界不能有除了 p 和 q 的其它点
 */