package LC3901_4200;
import java.util.*;
public class LC4077_MinimizeInversionsinaPrefixTree {
    /**
     * You are given an array words of n unique strings containing only lowercase English letters from 'a' to 'j'. You
     * are also given an integer array target containing each index from 0 to n - 1 exactly once.
     *
     * Build a prefix tree from words:
     *
     * The root represents the empty string "".
     * Each other node represents a different non-empty prefix of a word. The parent of a node is the prefix obtained by
     * removing its last character.
     * The node representing the complete word words[i] is marked with index i. A marked node may also have children.
     * You may choose the order of the children of each node independently. You cannot change which node is a child's
     * parent.
     *
     * Traverse the tree starting at the root. When visiting a node, first record its index if it is marked. Then visit
     * its children in the chosen order, completing the traversal of each child before moving to the next. This is a
     * pre-order traversal.
     *
     * The recorded indices form an array traversal of length n. Let pos[i] be the position of index i in target.
     *
     * An inversion is a pair of positions (a, b) such that 0 <= a < b < n and pos[traversal[a]] > pos[traversal[b]].
     *
     * Return the minimum possible number of inversions over all choices of child orderings.
     *
     * A prefix of a string is obtained by removing zero or more characters from the end of the string.
     *
     * Input: words = ["bad","bag","fig","fed"], target = [3,1,0,2]
     * Output: 2
     *
     * Input: words = ["ab","ac","ba","bc"], target = [0,3,1,2]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n == words.length <= 10^4
     * 1 <= words[i].length <= 20
     * words[i] contains only lowercase English letters from 'a' to 'j'
     * All words[i] are unique
     * 0 <= target[i] <= n - 1
     * target is a permutation of integers from 0 to n - 1
     * @param words
     * @param target
     * @return
     */
    // time = O(n * L * 10^2 * 2^10 + n * L * logn), space = O(n * L * 10 + 2^10), L: <= 20, 单个字符串的最大长度
    TrieNode root;
    int res;
    public int minInversions(String[] words, int[] target) {
        int n = target.length;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) pos[target[i]] = i;

        root = new TrieNode();
        for (int i = 0; i < n; i++) insert(words[i], pos[i]);
        dfs(root);
        return res;
    }

    private List<Integer> dfs(TrieNode p) {
        List<List<Integer>> q = new ArrayList<>();
        for (TrieNode x : p.son) {
            if (x != null) q.add(dfs(x));
        }

        // 预处理逆序对个数
        int m = q.size();
        int[][] inv = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (j != i) {
                    inv[i][j] = cal(q.get(i), q.get(j));
                }
            }
        }

        int[] f = new int[1 << m];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;

        for (int state = 0; state < 1 << m; state++) { // 之前选的下标集合是 state
            // 在 state 的右边添加 j
            for (int j = 0; j < m; j++) {
                if ((state >> j & 1) > 0) continue; // 已经选过了，pass
                int sum = f[state];
                // 枚举 state 中的下标 i,计算新产生的逆序对个数
                for (int i = 0; i < m; i++) {
                    if ((state >> i & 1) > 0) sum += inv[i][j]; // 可以组成配对，计算在内
                }
                f[state | 1 << j] = Math.min(f[state | 1 << j], sum);
            }
        }
        res += f[(1 << m) - 1];

        List<Integer> list = new ArrayList<>();
        int cur = p.idx;
        if (cur >= 0) list.add(cur);
        for (List<Integer> x : q) {
            if (cur >= 0) res += find(x, cur);
            list.addAll(x);
        }
        Collections.sort(list);
        return list;
    }

    private int cal(List<Integer> a, List<Integer> b) {
        int n = a.size(), m = b.size(), i = 0, j = 0, inv = 0;
        while (i < n && j < m) {
            if (a.get(i) > b.get(j)) {
                inv += n - i;
                j++;
            } else i++;
        }
        return inv;
    }

    private int find(List<Integer> a, int t) {
        int l = 0, r = a.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (a.get(mid) >= t) r = mid;
            else l = mid + 1;
        }
        return a.get(r) >= t ? r : r + 1;
    }

    private void insert(String s, int idx) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (p.son[u] == null) p.son[u] = new TrieNode();
            p = p.son[u];
        }
        p.idx = idx;
    }

    class TrieNode {
        TrieNode[] son = new TrieNode[10];
        int idx = -1;
    }
}
/**
 * 1.题意
 *  首先，字典树的每个 words[i] 的 end 节点直接保存 pos[i]
 *  找到某个 DFS 顺序，使得收集到的 pos 值的逆序对个数尽量小
 * 10! -> 2^10
 */