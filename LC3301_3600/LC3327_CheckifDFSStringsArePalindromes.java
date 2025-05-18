package LC3301_3600;
import java.util.*;
public class LC3327_CheckifDFSStringsArePalindromes {
    /**
     * You are given a tree rooted at node 0, consisting of n nodes numbered from 0 to n - 1. The tree is represented
     * by an array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
     *
     * You are also given a string s of length n, where s[i] is the character assigned to node i.
     *
     * Consider an empty string dfsStr, and define a recursive function dfs(int x) that takes a node x as a parameter
     * and performs the following steps in order:
     *
     * Iterate over each child y of x in increasing order of their numbers, and call dfs(y).
     * Add the character s[x] to the end of the string dfsStr.
     * Note that dfsStr is shared across all recursive calls of dfs.
     *
     * You need to find a boolean array answer of size n, where for each index i from 0 to n - 1, you do the following:
     *
     * Empty the string dfsStr and call dfs(i).
     * If the resulting string dfsStr is a palindrome, then set answer[i] to true. Otherwise, set answer[i] to false.
     * Return the array answer.
     *
     * A palindrome is a string that reads the same forward and backward.
     *
     * Input: parent = [-1,0,0,1,1,2], s = "aababa"
     * Output: [true,true,false,true,true,true]
     *
     * Input: parent = [-1,0,0,1,1,2], s = "aababa"
     * Output: [true,true,false,true,true,true]
     *
     * Constraints:
     *
     * n == parent.length == s.length
     * 1 <= n <= 10^5
     * 0 <= parent[i] <= n - 1 for all i >= 1.
     * parent[0] == -1
     * parent represents a valid tree.
     * s consists only of lowercase English letters.
     * @param parent
     * @param s
     * @return
     */
    // S1: String Hash
    // time = O(n), space = O(n)
    final int P = 131;
    List<Integer>[] adj;
    String s;
    boolean[] res;
    StringBuilder sb;
    int[] left, right;
    long[] h1, h2, p;
    public boolean[] findAnswer(int[] parent, String s) {
        this.s = s;
        int n = parent.length;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (parent[i] == -1) continue;
            int fa = parent[i];
            adj[fa].add(i);
        }

        res = new boolean[n];
        left = new int[n];
        right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        sb = new StringBuilder();
        dfs(0);
        String t = sb.toString();

        p = new long[n + 1];
        h1 = new long[n + 1];
        h2 = new long[n + 2];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h1[i] = h1[i - 1] * P + t.charAt(i - 1);
        }

        for (int i = n; i >= 1; i--) {
            h2[i] = h2[i + 1] * P + t.charAt(i - 1);
        }

        for (int i = 0; i < n; i++) {
            if (isPalin(left[i], right[i])) res[i] = true;
        }

        return res;
    }

    private boolean isPalin(int l, int r) {
        if (l > r) return true;
        return get(l, r, false) == get(l, r, true);
    }

    private long get(int l, int r, boolean rev) {
        l++;
        r++;
        if (!rev) return h1[r] - h1[l - 1] * p[r - l + 1];
        return h2[l] - h2[r + 1] * p[r - l + 1];
    }

    private void dfs(int u) {
        left[u] = sb.length();
        for (int v : adj[u]) dfs(v);
        sb.append(s.charAt(u));
        right[u] = sb.length() - 1;
    }

    // S2: Manacher
    // time = O(n), space = O(n)
    class Solution {
        List<Integer>[] adj;
        StringBuilder sb;
        public boolean[] findAnswer(int[] parent, String s) {
            int n = parent.length;
            adj = new List[n];
            for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (parent[i] != -1) adj[parent[i]].add(i);
            }

            sb = new StringBuilder();
            int[] lb = new int[n], rb = new int[n];
            dfs(s, 0, lb, rb);

            int[] p = manacher(sb.toString());
            boolean[] res = new boolean[n];
            for (int i = 0; i < n; i++) {
                int l = lb[i], r = rb[i];
                if (p[l + r + 1] - 1 >= r - l + 1) res[i] = true;
            }
            return res;
        }

        private void dfs(String s, int u, int[] lb, int[] rb) {
            lb[u] = sb.length();
            for (int v : adj[u]) dfs(s, v, lb, rb);
            sb.append(s.charAt(u));
            rb[u] = sb.length() - 1;
        }

        private int[] manacher(String s) {
            StringBuilder sb = new StringBuilder();
            sb.append('#');
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                sb.append(c).append('#');
            }

            int n = sb.length();
            int[] r = new int[n];
            for (int i = 0, j = 0; i < n; i++) {
                if (2 * j - i >= 0 && j + r[j] > i) {
                    r[i] = Math.min(r[2 * j - i], j + r[j] - i);
                }
                while (i - r[i] >= 0 && i + r[i] < n && sb.charAt(i - r[i]) == sb.charAt(i + r[i])) {
                    r[i]++;
                }
                if (i + r[i] > j + r[j]) j = i;
            }
            return r;
        }
    }
}
/**
 * 如何O(1)判断任意子串是否回文
 * 预处理：马拉车算法
 * ti = 2*si + 2 => si = (ti - 2) / 2
 * s[L,R] -> t[2L+2,2R+2] => 回文中心 = L+R+2 => halfLen[L+R+2]
 */