package LC3001_3300;
import java.util.*;
public class LC3076_ShortestUncommonSubstringinanArray {
    /**
     * You are given an array arr of size n consisting of non-empty strings.
     *
     * Find a string array answer of size n such that:
     *
     * answer[i] is the shortest substring of arr[i] that does not occur as a substring in any other string in arr.
     * If multiple such substrings exist, answer[i] should be the lexicographically smallest. And if no such substring
     * exists, answer[i] should be an empty string.
     * Return the array answer.
     *
     * Input: arr = ["cab","ad","bad","c"]
     * Output: ["ab","","ba",""]
     *
     * Input: arr = ["abc","bcd","abcd"]
     * Output: ["","","abcd"]
     *
     * Constraints:
     *
     * n == arr.length
     * 2 <= n <= 100
     * 1 <= arr[i].length <= 20
     * arr[i] consists only of lowercase English letters.
     * @param arr
     * @return
     */
    // time = O(n * m^3), space = O(n * m)
    public String[] shortestSubstrings(String[] arr) {
        int n = arr.length;
        HashMap<String, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int m = arr[i].length();
            for (int len = 1; len <= m; len++) {
                for (int j = 0; j + len - 1 < m; j++) {
                    String t = arr[i].substring(j, j + len);
                    map.putIfAbsent(t, new HashSet<>());
                    map.get(t).add(i);
                }
            }
        }

        String[] res = new String[n];
        Arrays.fill(res, "");
        for (int i = 0; i < n; i++) {
            int m = arr[i].length();
            boolean flag = false;
            for (int len = 1; len <= m; len++) {
                for (int j = 0; j + len - 1 < m; j++) {
                    String t = arr[i].substring(j, j + len);
                    if (map.get(t).size() == 1) {
                        for (int x : map.get(t)) {
                            if (x == i) {
                                if (res[i].length() == 0) res[i] = t;
                                else if (res[i].compareTo(t) > 0) res[i] = t;
                            }
                        }
                    }
                }
                if (res[i].length() > 0) break;
            }
        }
        return res;
    }
}
/**
 * 1. 不用哈希表
 * 计算arr[i]的答案
 * 按照子串长度枚举，内层枚举子串结束为止
 * 对于每个子串，遍历不是 arr[i] 的其余子串
 *
 * 2. 用哈希表
 * 把每个 arr[i] 的每个子串丢到一个哈希表中 (统计子串出现次数)
 * 再枚举 arr[i], 先把arr[i]的子串从哈希表中去掉
 * 按照子串长度枚举，内层枚举子串结束为止，取看子串是否在哈希表(出现次数 > 0)
 * 再把 arr[i] 子串到哈希表中
 *
 * 后缀数组 SA 后缀自动机 SAM
 * 可以做到线性复杂度
 */