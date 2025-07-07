package LC3601_3900;
import java.util.*;
public class LC3606_CouponCodeValidator {
    /**
     * You are given three arrays of length n that describe the properties of n coupons: code, businessLine, and
     * isActive. The ith coupon has:
     *
     * code[i]: a string representing the coupon identifier.
     * businessLine[i]: a string denoting the business category of the coupon.
     * isActive[i]: a boolean indicating whether the coupon is currently active.
     * A coupon is considered valid if all of the following conditions hold:
     *
     * code[i] is non-empty and consists only of alphanumeric characters (a-z, A-Z, 0-9) and underscores (_).
     * businessLine[i] is one of the following four categories: "electronics", "grocery", "pharmacy", "restaurant".
     * isActive[i] is true.
     * Return an array of the codes of all valid coupons, sorted first by their businessLine in the order:
     * "electronics", "grocery", "pharmacy", "restaurant", and then by code in lexicographical (ascending) order within
     * each category.
     *
     * Input: code = ["SAVE20","","PHARMA5","SAVE@20"], businessLine = ["restaurant","grocery","pharmacy","restaurant"],
     * isActive = [true,true,true,true]
     * Output: ["PHARMA5","SAVE20"]
     *
     * Input: code = ["GROCERY15","ELECTRONICS_50","DISCOUNT10"], businessLine = ["grocery","electronics","invalid"],
     * isActive = [false,true,true]
     * Output: ["ELECTRONICS_50"]
     *
     * Constraints:
     *
     * n == code.length == businessLine.length == isActive.length
     * 1 <= n <= 100
     * 0 <= code[i].length, businessLine[i].length <= 100
     * code[i] and businessLine[i] consist of printable ASCII characters.
     * isActive[i] is either true or false.
     * @param code
     * @param businessLine
     * @param isActive
     * @return
     */
    // time = O(nlogn), space = O(n)
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        List<String> res = new ArrayList<>();
        List<Node> q = new ArrayList<>();
        int n = code.length;

        for (int i = 0; i < n; i++) {
            String s = code[i], t = businessLine[i];
            boolean f = isActive[i];
            if (!check(s)) continue;
            int v = 0;
            if (t.equals("electronics")) v = 0;
            else if (t.equals("grocery")) v = 1;
            else if (t.equals("pharmacy")) v = 2;
            else if (t.equals("restaurant")) v = 3;
            else continue;
            if (!f) continue;
            q.add(new Node(s, v));
        }
        Collections.sort(q, (o1, o2) -> o1.v != o2.v ? o1.v - o2.v : o1.s.compareTo(o2.s));
        for (Node x : q) res.add(x.s);
        return res;
    }

    private boolean check(String s) {
        int n = s.length();
        if (n == 0) return false;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c) || Character.isDigit(c) || c == '_') continue;
            return false;
        }
        return true;
    }

    class Node {
        String s;
        int v;
        public Node(String s, int v) {
            this.s = s;
            this.v = v;
        }
    }
}
/**
 * 1. 判断优惠码是否合法
 *  1. code[i] 不能为空
 *  2. 仅由字母数字字符 (a-z, A-Z, 0-9) 和下划线(_)组成
 *  3. 类别 businessLine[i] 必须是四种类型之一
 *  4. isActive[i] == true
 * 2. 排序优惠码
 *  1. 按照类别(映射到一个编号上)从小到大排序
 *  2. 想通类别，(按照字典序)从小到大排序
 */