package LC3901_4200;
import java.util.*;
public class LC4082_MinimumOperationstoStabilizeaBinaryTree {
    /**
     * You are given the root of a binary tree. Each node contains an integer value representing its current state.
     *
     * A binary tree is considered stable if for every node u:
     *
     * If u.left exists, then |u.val - u.left.val| <= 1
     * If u.right exists, then |u.val - u.right.val| <= 1
     * In one operation, you may choose any node and increase or decrease its value by 1.
     *
     * Return the minimum number of operations required to make the entire binary tree stable.
     *
     * Input: root = [5,8,3]
     * Output: 3
     *
     * Input: root = [2,-1,-4]
     * Output: 5
     *
     * Input: root = [0,-2,2,-3,null,1]
     * Output: 3
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 5 * 10^4].
     * -10^5 <= Node.val <= 10^5
     * @param root
     * @return
     */
    // time = O(n * (logn)^2), space = O(n)
    public long stabilizeBinaryTree(TreeNode root) {
        List<TreeNode> order = new ArrayList<>();
        Deque<TreeNode> stk = new LinkedList<>();
        stk.push(root);
        while (!stk.isEmpty()) {
            TreeNode u = stk.pop();
            order.add(u);
            if (u.left != null) stk.push(u.left);
            if (u.right != null) stk.push(u.right);
        }

        HashMap<TreeNode, S> map = new HashMap<>();
        for (int i = order.size() - 1; i >= 0; i--) {
            TreeNode u = order.get(i);
            S cur = null;
            if (u.left != null) {
                S c = map.remove(u.left);
                widen(c);
                cur = c;
            }
            if (u.right != null) {
                S c = map.remove(u.right);
                widen(c);
                cur = cur == null ? c : merge(cur, c);
            }
            if (cur == null) cur = new S();

            // add |x - val|
            cur.A += u.val;
            cur.k++;
            insert(cur, u.val);
            insert(cur, u.val);
            rebalance(cur);
            map.put(u, cur);
        }

        S r = map.get(root);
        return r.A - (r.sumL + r.addL * r.L.size());
    }

    class S {
        PriorityQueue<Long> L = new PriorityQueue<>((o1, o2) -> Long.compare(o2, o1));
        PriorityQueue<Long> R = new PriorityQueue<>();
        long addL = 0, addR = 0, sumL = 0, A = 0;
        int k = 0;
        int size() {
            return L.size() + R.size();
        }
    }

    private void insert(S s, long x) {
        boolean toL;
        if (!s.L.isEmpty()) toL = x <= s.L.peek() + s.addL;
        else toL = s.R.isEmpty() || x <= s.R.peek() + s.addR;
        if (toL) {
            s.L.add(x - s.addL);
            s.sumL += x - s.addL;
        } else s.R.add(x - s.addR);
    }

    private void rebalance(S s) {
        while (s.L.size() > s.k) {
            long v = s.L.poll();
            s.sumL -= v;
            long actual = v + s.addL;
            s.R.add(actual - s.addR);
        }

        while (s.L.size() < s.k) {
            long v = s.R.poll();
            long actual = v + s.addR;
            long raw = actual - s.addL;
            s.L.add(raw);
            s.sumL += raw;
        }
    }

    private void widen(S s) {
        s.A -= s.k;
        s.addL--;
        s.addR++;
    }

    private S merge(S a, S b) {
        if (a.size() < b.size()) {
            S t = a;
            a = b;
            b = t;
        }
        a.k += b.k;
        a.A += b.A;
        for (long v : b.L) insert(a, v + b.addL);
        for (long v : b.R) insert(a, v + b.addR);
        rebalance(a);
        return a;
    }
}
