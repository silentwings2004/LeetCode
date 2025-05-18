package LC901_1200;
import java.util.*;
public class LC947_MostStonesRemovedwithSameRoworColumn {
    /**
     * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
     *
     * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
     *
     * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return
     * the largest possible number of stones that can be removed.
     *
     * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= stones.length <= 1000
     * 0 <= xi, yi <= 10^4
     * No two stones are at the same coordinate point.
     * @param stones
     * @return
     */
    // time = O(nlogk), space = O(k)   n: num of stones; k: different (x,y) numbers of array stones
    int[] p;
    public int removeStones(int[][] stones) {
        int n = stones.length;
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;

        HashMap<Integer, List<Integer>> xMap = new HashMap<>();
        HashMap<Integer, List<Integer>> yMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = stones[i][0], y = stones[i][1];
            xMap.putIfAbsent(x, new ArrayList<>());
            yMap.putIfAbsent(y, new ArrayList<>());
            xMap.get(x).add(i);
            yMap.get(y).add(i);
        }

        for (List<Integer> v : xMap.values()) {
            for (int x : v) p[find(v.get(0))] = find(x);
        }

        for (List<Integer> v : yMap.values()) {
            for (int x : v) p[find(v.get(0))] = find(x);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i != p[i]) res++;
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
/**
 * x x x x   xx
 *   x
 *   x
 *   x
 *   x
 *   x
 * 凡是同一行，就是一个group，同一个列也是一个group
 * 只要是同一个group,有k个元素，那一定能remove k - 1个元素
 * 每个group都拿到只剩1个，只有每个group只有最后一个拿不掉
 * total removed = all stones - group number
 * 不停通过两两之间union
 * follow-up: 这些stone移走的顺序是什么？
 *       z
 *       z
 *       z
 * xxxxx xx x xx
 *   y
 *   y
 *   y
 * 从角落里开始移除直到交叉路口停下来
 * 找那些入度为1的点先移除，最后只剩一行，从左到右删除到只剩1个即可
 *
 * 转化成图论问题
 * 对于每一个连通块而言，一定存在一棵生成树
 * 每次删除生成树的叶节点，最后一定剩下一个根节点
 * => 答案就是生成树的数量 => 连通块的数量
 */
