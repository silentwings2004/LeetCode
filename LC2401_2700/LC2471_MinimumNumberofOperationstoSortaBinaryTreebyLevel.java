package LC2401_2700;
import java.util.*;
public class LC2471_MinimumNumberofOperationstoSortaBinaryTreebyLevel {
    /**
     * You are given the root of a binary tree with unique values.
     *
     * In one operation, you can choose any two nodes at the same level and swap their values.
     *
     * Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing
     * order.
     *
     * The level of a node is the number of edges along the path between it and the root node.
     *
     * Input: root = [1,4,3,7,6,8,5,null,null,null,null,9,null,10]
     * Output: 3
     *
     * Input: root = [1,3,2,7,6,5,4]
     * Output: 3
     *
     * Input: root = [1,2,3,4,5,6]
     * Output: 0
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^5].
     * 1 <= Node.val <= 10^5
     * All the values of the tree are unique.
     * @param root
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    int[] p;
    public int minimumOperations(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> w = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            ls.add(w.size());
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                w.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int n = w.size();
        p = new int[n];
        for (int i = 0; i < n; i++) {
            map.put(w.get(i), i);
            p[i] = i;
        }

        ls.add(n);
        int m = ls.size();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = w.get(i);
        for (int i = 0; i + 1 < m; i++) {
            Arrays.sort(nums, ls.get(i), ls.get(i + 1)); // 分成sort
        }

        int cnt = n;
        for (int i = 0; i < n; i++) {
            int a = find(i), b = find(map.get(nums[i])); // a: 排序后的元素，b:排序前的元素 -> 属于一个集合
            if (a != b) {
                p[a] = b;
                cnt--;
            }
        }
        return n - cnt; // 求需要打破的配对数 == 交换次数
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int minimumOperations2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] w = new int[size];
            int idx = 0;
            TreeMap<Integer, Integer> map = new TreeMap<>();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                map.put(cur.val, idx);
                w[idx++] = cur.val;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            idx = 0;
            for (int x : map.keySet()) {
                if (map.get(x) == idx) {
                    idx++;
                    continue;
                }
                int a = map.get(x);
                map.put(x, idx);
                map.put(w[idx], a);
                w[a] = w[idx];
                w[idx] = x;
                res++;
                idx++;
            }
        }
        return res;
    }

    // S3: indexing sort + Greedy
    // time = O(nlogn), space = O(n)
    final int N = 100010;
    List<Integer>[] level;
    int maxDepth;
    public int minimumOperations3(TreeNode root) {
        level = new List[N];
        for (int i = 0; i < N; i++) level[i] = new ArrayList<>();
        maxDepth = 0;

        dfs(root, 0);

        int count = 0;
        for (int t = 0; t <= maxDepth; t++) {
            List<Integer> q = level[t];
            int n = q.size();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) nums[i] = q.get(i);
            int[] sorted = nums.clone();
            Arrays.sort(sorted);
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) map.put(sorted[i], i);
            for (int i = 0; i < n; i++) {
                while (map.get(nums[i]) != i) {
                    swap(nums, i, map.get(nums[i]));
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) return;

        level[depth].add(node.val);
        maxDepth = Math.max(maxDepth, depth);
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
/**
 * 环图模型
 * 交换2个数，对环图的影响
 * 1.交换2个数在不同的环里 -> 合并成一个环
 * 2. 交换2个数在同一个环里 -> 将1个环拆成2个环
 * 结果是拆成n个自环 => 操作2
 * 统计当前有多少个环 => n - cnt次
 * 并查集求环 => 环图里连通块的数量
 *
 * indexing sort
 * nums[i]: 1 ~ n => O(n)
 * 尽量让它的数值与它的index 吻合起来
 */