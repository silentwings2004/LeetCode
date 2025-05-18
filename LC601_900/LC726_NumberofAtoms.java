package LC601_900;
import java.util.*;
public class LC726_NumberofAtoms {
    /**
     * Given a string formula representing a chemical formula, return the count of each atom.
     *
     * The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing
     * the name.
     *
     * One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1,
     * no digits will follow.
     *
     * For example, "H2O" and "H2O2" are possible, but "H1O2" is impossible.
     * Two formulas are concatenated together to produce another formula.
     *
     * For example, "H2O2He3Mg4" is also a formula.
     * A formula placed in parentheses, and a count (optionally added) is also a formula.
     *
     * For example, "(H2O2)" and "(H2O2)3" are formulas.
     * Return the count of all elements as a string in the following form: the first name (in sorted order), followed by
     * its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count
     * (if that count is more than 1), and so on.
     *
     * The test cases are generated so that all the values in the output fit in a 32-bit integer.
     *
     * Input: formula = "H2O"
     * Output: "H2O"
     *
     * Constraints:
     *
     * 1 <= formula.length <= 1000
     * formula consists of English letters, digits, '(', and ')'.
     * formula is always valid.
     * @param formula
     * @return
     */
    // S1
    // time = O(n^2), space = O(n)
    public String countOfAtoms(String formula) {
        Stack<TreeMap<String, Integer>> stack = new Stack<>(); // 元素种类与个数
        TreeMap<String, Integer> map = new TreeMap<>();
        int n = formula.length();
        for (int i = 0; i < n; i++) { // O(n)
            if (formula.charAt(i) == '(') {
                stack.push(new TreeMap<>(map));
                map.clear();
            } else if (formula.charAt(i) == ')') {
                int j = i + 1;
                while (j < n && Character.isDigit(formula.charAt(j))) j++;
                int num = 0;
                if (j == i + 1) num = 1;
                else num = Integer.parseInt(formula.substring(i + 1, j));

                for (String key : map.keySet()) {  // O(n)
                    map.put(key, map.get(key) * num);
                }

                // merge with the top of stack
                for (String key : stack.peek().keySet()) {
                    map.put(key, map.getOrDefault(key, 0) + stack.peek().get(key));
                }
                stack.pop();
                i = j - 1;
            } else if (Character.isUpperCase(formula.charAt(i))){
                int j = i + 1;
                while (j < n && Character.isLowerCase(formula.charAt(j))) j++;
                String element = formula.substring(i, j);
                i = j - 1;
                while (j < n && Character.isDigit(formula.charAt(j))) j++;
                int num = 0;
                if (j == i + 1) num = 1;
                else num = Integer.parseInt(formula.substring(i + 1, j));
                map.put(element, map.getOrDefault(element, 0) + num);
                i = j - 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            sb.append(key);
            if (map.get(key) > 1) sb.append(map.get(key)); // 分子式中的下标数字为1时会缺省。
        }
        return sb.toString();
    }

    // S2
    // time = O(n^2), space = O(n)
    int u = 0;
    public String countOfAtoms2(String formula) {
        TreeMap<String, Integer> map = dfs(formula);
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            sb.append(key);
            if (map.get(key) > 1) sb.append(map.get(key));
        }
        return sb.toString();
    }

    private TreeMap<String, Integer> dfs(String str) {
        TreeMap<String, Integer> map = new TreeMap<>();
        int n = str.length();
        while (u < n) {
            if (str.charAt(u) == '(') {
                u++;
                TreeMap<String, Integer> t = dfs(str);
                u++;
                int cnt = 1, k = u;
                while (k < n && Character.isDigit(str.charAt(k))) k++;
                if (k > u) {
                    cnt = Integer.parseInt(str.substring(u, k));
                    u = k;
                }
                for (String x : t.keySet()) map.put(x, map.getOrDefault(x, 0) + t.get(x) * cnt);

            } else if (str.charAt(u) == ')') break;
            else {
                int k = u + 1;
                while (k < n && Character.isLowerCase(str.charAt(k))) k++;
                String key = str.substring(u, k);
                u = k;
                int cnt = 1;
                while (k < n && Character.isDigit(str.charAt(k))) k++;
                if (k > u) {
                    cnt = Integer.parseInt(str.substring(u, k));
                    u = k;
                }
                map.put(key, map.getOrDefault(key, 0) + cnt);
            }
        }
        return map;
    }

    // S3
    // time = O(n^2), space = O(n)
    public String countOfAtoms3(String formula) {
        int n = formula.length(), d = 0;
        HashMap<Integer, List<Pair>> hash = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = formula.charAt(i);
            if (Character.isUpperCase(c)) {
                if (sb.length() > 0) {
                    hash.putIfAbsent(d, new ArrayList<>());
                    hash.get(d).add(new Pair(sb.toString(), 1));
                    sb = new StringBuilder();
                }
                sb.append(c);
            } else if (Character.isLowerCase(c)) sb.append(c);
            else if (Character.isDigit(c)) {
                int v = 0, j = i;
                while (j < n && Character.isDigit(formula.charAt(j))) {
                    v = v * 10 + formula.charAt(j++) - '0';
                }
                String t = sb.toString();
                sb = new StringBuilder();
                hash.putIfAbsent(d, new ArrayList<>());
                hash.get(d).add(new Pair(t, v));
                i = j - 1;
            } else if (c == '(') {
                if (sb.length() > 0) {
                    String t = sb.toString();
                    sb = new StringBuilder();
                    hash.putIfAbsent(d, new ArrayList<>());
                    hash.get(d).add(new Pair(t, 1));
                }
                d++;
            } else {
                if (sb.length() > 0) {
                    String t = sb.toString();
                    sb = new StringBuilder();
                    hash.putIfAbsent(d, new ArrayList<>());
                    hash.get(d).add(new Pair(t, 1));
                }
                int v = 0, j = i + 1;
                while (j < n && Character.isDigit(formula.charAt(j))) {
                    v = v * 10 + formula.charAt(j++) - '0';
                }
                if (v == 0) v = 1;
                i = j - 1;
                for (Pair x : hash.getOrDefault(d, new ArrayList<>())) {
                    String t = x.str;
                    int num = x.v;
                    hash.putIfAbsent(d - 1, new ArrayList<>());
                    hash.get(d - 1).add(new Pair(t, num * v));
                }
                hash.remove(d);
                d--;
            }
        }
        if (sb.length() > 0) {
            hash.putIfAbsent(d, new ArrayList<>());
            hash.get(d).add(new Pair(sb.toString(), 1));
        }

        TreeMap<String, Integer> map = new TreeMap<>();
        for (Pair x : hash.get(0)) {
            map.put(x.str, map.getOrDefault(x.str, 0) + x.v);
        }
        sb = new StringBuilder();
        for (String key : map.keySet()) {
            sb.append(key);
            if (map.get(key) > 1) sb.append(map.get(key));
        }
        return sb.toString();
    }

    class Pair {
        String str;
        int v;
        public Pair(String str, int v) {
            this.str = str;
            this.v = v;
        }
    }
}
/**
 * 典型的栈的应用。此题的特别之处在于stack的元素应该是map<string,int>.
 * 遍历formula的元素，分别对formula[i]=='(', ')', '大写字母'三种情况进行讨论。注意分子式中的下标数字为1时会缺省。
 */