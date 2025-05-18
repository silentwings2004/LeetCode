package LC301_600;
import java.util.*;
public class LC403_FrogJump {
    /**
     * A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may
     * not exist a stone. The frog can jump on a stone, but it must not jump into the water.
     *
     * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog can cross the
     * river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must
     * be 1 unit.
     *
     * If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only
     * jump in the forward direction.
     *
     * Input: stones = [0,1,3,5,6,8,12,17]
     * Output: true
     *
     * Constraints:
     *
     * 2 <= stones.length <= 2000
     * 0 <= stones[i] <= 2^31 - 1
     * stones[0] == 0
     * stones is sorted in a strictly increasing order.
     * @param stones
     * @return
     */
    // time = O(n^2), space = O(n^2)
    HashSet<Integer> set;
    HashSet<String> failed;
    public boolean canCross(int[] stones) {
        set = new HashSet<>();
        failed = new HashSet<>();
        for (int x : stones) set.add(x);
        return dfs(stones, 0, 0);
    }

    private boolean dfs(int[] stones, int pos, int jump) {
        int n = stones.length;
        // base case
        if (pos == stones[n - 1]) return true;
        if (!set.contains(pos)) return false;
        if (failed.contains(pos + "#" + jump)) return false;

        if (jump > 1 && dfs(stones, pos + jump - 1, jump - 1)) return true;
        if (jump > 0 && dfs(stones, pos + jump, jump)) return true;
        if (dfs(stones, pos + jump + 1, jump + 1)) return true;
        failed.add(pos + "#" + jump);
        return false;
    }

    // S2
    final int N = 2010;
    Boolean[][] f;
    int[] stones;
    HashMap<Integer, Integer> map;
    public boolean canCross2(int[] stones) {
        this.stones = stones;
        map = new HashMap<>();
        f = new Boolean[N][N];
        f[0][1] = true;
        int n = stones.length;
        for (int i = 0; i < n; i++) map.put(stones[i], i);
        for (int i = 0; i < n - 1; i++) {
            if (dp(n - 1, i)) return true;
        }
        return false;
    }

    private boolean dp(int i, int j) {
        if (f[i][j] != null) return f[i][j];
        f[i][j] = false;
        for (int k = Math.max(1, j - 1); k <= j + 1; k++) {
            if (map.containsKey(stones[i] - k)) {
                int p = map.get(stones[i] - k);
                if (dp(p, k)) {
                    f[i][j] = true;
                    break;
                }
            }
        }
        return f[i][j];
    }
}
/**
 * 比较直观的想法就是DFS搜索，层层递归下去。
 * 设计递归函数 bool DFS(pos,jump)表示当青蛙以jump的跨度跳到pos的石头上时，它能否跳到最后。
 * 显然，如果此刻的pos不是最后一块石头的话，
 * 那么就继续考察 DFS(pos+jump-1,jump-1), DFS(pos+jump,jump), DFS(pos+jump+1,jump+1)即可。
 * 需要注意的细节：
 * 1. 如果pos不是石头的位置，直接返回false，
 * 2.下一步的跨度不能小于等于0，否则就死循环。
 * 如果思考进一步优化的算法，那显然就是记忆化，把每次搜索过的失败都记录下来。
 * 很容易想到，将已经探索过的{pos,jump}共同作为一个key存在一个集合FailureSet里，表明这个状态是失败的，
 * 以后DFS过程遇到这个状态就直接返回false。
 *
 * dp
 * 状态表示 f(i,j): 从第i个石头开始跳，跳j的距离是否合法
 * j-1,j,j+1
 * 上一步的位置是Si-k，是否有石头代表是否合法
 * f(p,k)
 */
