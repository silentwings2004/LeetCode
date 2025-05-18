package LC2700_3000;
import java.util.*;
public class LC2791_CountPathsThatCanFormaPalindromeinaTree {
    /**
     * You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n
     * nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i]
     * is the parent of node i. Since node 0 is the root, parent[0] == -1.
     *
     * You are also given a string s of length n, where s[i] is the character assigned to the edge between i and
     * parent[i]. s[0] can be ignored.
     *
     * Return the number of pairs of nodes (u, v) such that u < v and the characters assigned to edges on the path from
     * u to v can be rearranged to form a palindrome.
     *
     * A string is a palindrome when it reads the same backwards as forwards.
     *
     * Input: parent = [-1,0,0,1,1,2], s = "acaabc"
     * Output: 8
     *
     * Input: parent = [-1,0,0,0,0], s = "aaaaa"
     * Output: 10
     *
     * Constraints:
     *
     * n == parent.length == s.length
     * 1 <= n <= 10^5
     * 0 <= parent[i] <= n - 1 for all i >= 1
     * parent[0] == -1
     * parent represents a valid tree.
     * s consists of only lowercase English letters.
     * @param parent
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    List<int[]>[] g;
    HashMap<Integer, Integer> map;
    long res;
    public long countPalindromePaths(List<Integer> parent, String s) {
        int n = parent.size();
        g = new List[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = i, b = parent.get(i);
            int c = s.charAt(i) - 'a';
            if (b == -1) continue;
            g[b].add(new int[]{a, 1 << c});
        }

        map = new HashMap<>();
        map.put(0, 1);
        res = 0;
        dfs(0, 0);
        return res;
    }

    private void dfs(int u, int s) {
        for (int[] x : g[u]) {
            int j = x[0], t = x[1];
            int ns = s ^ t;
            res += map.getOrDefault(ns, 0);
            for (int i = 0; i < 26; i++) res += map.getOrDefault(ns ^ (1 << i), 0);
            map.put(ns, map.getOrDefault(ns, 0) + 1);
            dfs(j, ns);
        }
    }
}