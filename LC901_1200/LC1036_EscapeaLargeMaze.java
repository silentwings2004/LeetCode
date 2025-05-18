package LC901_1200;
import java.util.*;
public class LC1036_EscapeaLargeMaze {
    /**
     * There is a 1 million by 1 million grid on an XY-plane, and the coordinates of each grid square are (x, y).
     *
     * We start at the source = [sx, sy] square and want to reach the target = [tx, ty] square. There is also an array
     * of blocked squares, where each blocked[i] = [xi, yi] represents a blocked square with coordinates (xi, yi).
     *
     * Each move, we can walk one square north, east, south, or west if the square is not in the array of blocked
     * squares. We are also not allowed to walk outside of the grid.
     *
     * Return true if and only if it is possible to reach the target square from the source square through a sequence
     * of valid moves.
     *
     * Input: blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
     * Output: false
     *
     * Constraints:
     *
     * 0 <= blocked.length <= 200
     * blocked[i].length == 2
     * 0 <= xi, yi < 10^6
     * source.length == target.length == 2
     * 0 <= sx, sy, tx, ty < 10^6
     * source != target
     * It is guaranteed that source and target are not blocked.
     * @param blocked
     * @param source
     * @param target
     * @return
     */
    // time = O(b^2), space = O(b^2)  b = b * (b - 1) / 2
    HashSet<String> set;
    int m, n = (int)1e6;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        set = new HashSet<>();
        m = blocked.length;
        for (int[] b : blocked) set.add(get(b[0], b[1]));
        return bfs(source, target) && bfs(target, source);
    }

    private boolean bfs(int[] source, int[] target) {
        HashSet<String> st = new HashSet<>(set);
        Queue<int[]> q = new LinkedList<>();
        q.offer(source);
        st.add(get(source[0], source[1]));
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] t = q.poll();
            int x = t[0], y = t[1];
            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= n || b < 0 || b >= n) continue;
                if (st.contains(get(a, b))) continue;
                cnt++;
                if (cnt * 2 > m * (m - 1)) return true;
                if (target[0] == a && target[1] == b) return true;
                q.offer(new int[]{a, b});
                st.add(get(a, b));
            }
        }
        return false;
    }

    private String get(int x, int y) {
        return x + "#" + y;
    }
}
/**
 * 1M * 1M,很大，block的地方不能碰，能不能走到
 * 非常非常大的网格
 * 递归 -> 非常多层
 * 除了搜索没有更好的方法，但暴搜几乎不可能
 * 0 <= blocked.length <= 200
 * 不能进入的格子不超过200个
 * 极大概率可以绕开
 * 200个在什么情况下可以把起点和终点阻隔开
 * 比如把起点或者终点完全给封闭起来就没办法相遇了
 * 等所有都走遍了，队列就空了，说明肯定是外面把它完全封闭了，否则就能一直往外扩散
 * 什么时候判断它会清空
 * 如何判断永远不能被清空还是暂时不能被清空
 * 不可能永远无限制的把队列加下去
 * 有没有迹象表明确实是完全封闭的？
 * 队列里元素太多了？200个元素可以围成最大的区域面积是多少呢？
 * 考虑总周长为200的正方形，说明其边长是50，也就是从起点最多走49步。
 * 这种正方形最紧凑，是固定周长的条件下能够覆盖的最大的面积（2500）
 * 如果从起点出发向外扩散，并且最终可以扩展到超过2500个元素的区域（且没有遇到终点），这说明什么？
 * 因为根本没有周长是200的图形可以封闭住这么的面积，所以blocked对于这个起点而言是无效的。也就是说，blocked并不能完全围住起点。
 * 所以这是一个BFS题，只要从一个点出发开始发散，当visited的网格数目（也就是覆盖的面积）大于2500的时候，就说明这个点并没有被封闭。
 * 我们需要注意，其实200的周长最大能封闭的面积可以是19900，而不是2500.
 * 原因是这200个点可以以45度倾斜地围住一个角。因此0+1+2+...+199 = 19900才是最大的封闭面积。=> area = (0 + 199) * 200 / 2 = (n-1)*n/2
 * 只有发散的区域超过了这个面积，才能保证不被封闭。
 * 0th      _________________________
 *          |O O O O O O O X
 *          |O O O O O O X
 *          |O O O O O X
 *          |O O O O X
 *          .O O O X
 *          .O O X
 *          .O X
 * 200th    |X
 */