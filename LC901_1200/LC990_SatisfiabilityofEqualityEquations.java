package LC901_1200;

public class LC990_SatisfiabilityofEqualityEquations {
    /**
     * You are given an array of strings equations that represent relationships between variables where each string
     * equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are
     * lowercase letters (not necessarily different) that represent one-letter variable names.
     *
     * Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or
     * false otherwise.
     *
     * Input: equations = ["a==b","b!=a"]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= equations.length <= 500
     * equations[i].length == 4
     * equations[i][0] is a lowercase letter.
     * equations[i][1] is either '=' or '!'.
     * equations[i][2] is '='.
     * equations[i][3] is a lowercase letter.
     * @param equations
     * @return
     */
    // time = O(n), space = O(1)
    int[] p;
    public boolean equationsPossible(String[] equations) {
        p = new int[26];
        for (int i = 0; i < 26; i++) p[i] = i;
        for (String e : equations) {
            int a = e.charAt(0) - 'a';
            int b = e.charAt(3) - 'a';
            if (e.charAt(1) == '=') p[find(a)] = find(b);
        }

        for (String e : equations) {
            int a = e.charAt(0) - 'a';
            int b = e.charAt(3) - 'a';
            if (e.charAt(1) == '!') {
                if (find(a) == find(b)) return false;
            }
        }
        return true;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
/**
 * a != b, a == b
 * 把所有的等号放前面，先聚集起来，再处理不等号，看能否导出矛盾
 * trick: 先聚类，再看有无矛盾 -> two pass
 */