package LC3601_3900;
import java.util.*;
public class LC3900_LongestBalancedSubstringAfterOneSwap {
    /**
     * You are given a binary string s consisting only of characters '0' and '1'.
     *
     * A string is balanced if it contains an equal number of '0's and '1's.
     *
     * You can perform at most one swap between any two characters in s. Then, you select a balanced substring from s.
     *
     * Return an integer representing the maximum length of the balanced substring you can select.
     *
     * Input: s = "100001"
     * Output: 4
     *
     * Input: s = "111"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists only of the characters '0' and '1'.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public int longestBalanced(String s) {
        int n = s.length(), c0 = 0, c1 = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '0') c0++;
            else c1++;
        }
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        map.putIfAbsent(0, new ArrayList<>());
        map.get(0).add(-1);

        int res = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '1') sum++;
            else sum--;
            map.putIfAbsent(sum, new ArrayList<>());
            if (map.get(sum).size() < 2) map.get(sum).add(i);

            // 不交换
            res = Math.max(res, i - map.get(sum).get(0));

            // 交换子串内的一个 1 和子串外的一个 0
            List<Integer> q = map.get(sum - 2);
            if (q != null) {
                int len = i - q.get(0);
                int zeroInside = (len - 2) / 2;
                if (zeroInside < c0) res = Math.max(res, i - q.get(0));
                else if (q.size() > 1) res = Math.max(res, i - q.get(1));
            }
            // 交换子串内的一个 0 和子串外的一个 1
            q = map.get(sum + 2);
            if (q != null) {
                int len = i - q.get(0);
                int oneInside = (len - 2) / 2;
                if (oneInside < c1) res = Math.max(res, i - q.get(0));
                else if (q.size() > 1) res = Math.max(res, i - q.get(1));
            }
        }
        return res;
    }
}
/**
 * Ref: LC525 不操作，前缀和
 * 想知道子串中 0 的个数，1 的个数 -2 即可
 * s[r] - s[l] = 2
 * 找到最长的子串来满足
 * 找到一个最小的s[l]来满足条件，第一次出现的下标
 * 但万一外面没有 0，所有的 0 都在子串里面，就没法操作了
 * 如果判断子串外面有没有 0 => 求出子串里多少 0，一共多少个 0 => 就知道外面有没有 0 了
 * 如果子串外面没有 0 => 1 1 0 0 1 1 退而求其次 => 子串右端点不变，再找一个左端点
 * => 如果最小的 l 不满足条件，然就退而求其次再找一个大一点的 l 就可以了
 * 由于同一个前缀和两次出现的位置之间的元素和为 0，所以两个位置之间的 0 和 1 的个数相等且都大于 0
 * 所以长为 i−j 的这个子串的外面一定有 0，可以完成交换。
 */