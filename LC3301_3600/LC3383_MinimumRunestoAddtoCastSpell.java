package LC3301_3600;
import java.util.*;
public class LC3383_MinimumRunestoAddtoCastSpell {
    /**
     * Alice has just graduated from wizard school, and wishes to cast a magic spell to celebrate. The magic spell
     * contains certain focus points where magic needs to be concentrated, and some of these focus points contain magic
     * crystals which serve as the spell's energy source. Focus points can be linked through directed runes, which
     * channel magic flow from one focus point to another.
     *
     * You are given a integer n denoting the number of focus points and an array of integers crystals where crystals[i]
     * indicates a focus point which holds a magic crystal. You are also given two integer arrays flowFrom and flowTo,
     * which represent the existing directed runes. The ith rune allows magic to freely flow from focus point
     * flowFrom[i] to focus point flowTo[i].
     *
     * You need to find the number of directed runes Alice must add to her spell, such that each focus point either:
     *
     * Contains a magic crystal.
     * Receives magic flow from another focus point.
     * Return the minimum number of directed runes that she should add.
     *
     * Input: n = 6, crystals = [0], flowFrom = [0,1,2,3], flowTo = [1,2,3,0]
     * Output: 2
     *
     * Input: n = 7, crystals = [3,5], flowFrom = [0,1,2,3,5], flowTo = [1,2,0,4,6]
     * Output: 1
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * 1 <= crystals.length <= n
     * 0 <= crystals[i] <= n - 1
     * 1 <= flowFrom.length == flowTo.length <= min(2 * 105, (n * (n - 1)) / 2)
     * 0 <= flowFrom[i], flowTo[i] <= n - 1
     * flowFrom[i] != flowTo[i]
     * All pre-existing directed runes are distinct.
     * @param n
     * @param crystals
     * @param flowFrom
     * @param flowTo
     * @return
     */
    // time = O(n + m), space = O(n + m)
    List<Integer>[] adj, rev;
    boolean[] st;
    int[] stk;
    List<HashSet<Integer>> scc;
    int n, tt;
    public int minRunesToAdd(int n, int[] crystals, int[] flowFrom, int[] flowTo) {
        this.n = n;
        adj = new List[n];
        rev = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            rev[i] = new ArrayList<>();
        }
        stk = new int[n + 1];
        st = new boolean[n];
        scc = new ArrayList<>();
        tt = 0;

        int m = flowFrom.length;
        for (int i = 0; i < m; i++) {
            int a = flowFrom[i], b = flowTo[i];
            adj[a].add(b);
            rev[b].add(a);
        }

        findScc();
        int scc_cnt = scc.size();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < scc_cnt; i++) {
            for (int x : scc.get(i)) map.put(x, i);
        }
        boolean[] hin = new boolean[scc_cnt];
        boolean[] hac = new boolean[scc_cnt];
        for (int x : crystals) hac[map.get(x)] = true;
        for (int i = 0; i < n; i++) {
            for (int j : adj[i]) {
                int a = map.get(i), b = map.get(j);
                if (a != b) hac[b] = true;
            }
        }
        int res = 0;
        for (int i = 0; i < scc_cnt; i++) {
            if (!hin[i] && !hac[i]) res++;
        }
        return res;
    }

    private void dfs(int u) {
        st[u] = true;
        for (int v : adj[u]) {
            if (st[v]) continue;
            dfs(v);
        }
        stk[++tt] = u;
    }

    private void dfs2(int u, HashSet<Integer> set) {
        st[u] = true;
        set.add(u);
        for (int v : rev[u]) {
            if (st[v]) continue;
            dfs2(v, set);
        }
    }

    private void findScc() {
        Arrays.fill(st, false);
        for (int i = 0; i < n; i++) {
            if (st[i]) continue;
            dfs(i);
        }

        Arrays.fill(st, false);
        while (tt > 0) {
            int x = stk[tt--];
            if (st[x]) continue;
            HashSet<Integer> set = new HashSet<>();
            dfs2(x, set);
            scc.add(set);
        }
    }
}