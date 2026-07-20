package LC3901_4200;
import java.util.*;
public class LC3999_MinimumNumberofStringGroupsThroughTransformations {
    /**
     * You are given an array of strings words.
     *
     * Define a transformation on a string s as follows:
     *
     * Let E be the subsequence of characters at even indices of s.
     * Let O be the subsequence of characters at odd indices of s.
     * Independently cyclically shift E and O by any number of positions to the right, possibly zero.
     * Reconstruct the string by placing the shifted E characters back into even indices and the shifted O characters
     * back into odd indices.
     * Two strings are equivalent if one can be transformed into the other by a single transformation.
     *
     * Partition words into the minimum number of groups such that:
     *
     * Every string belongs to exactly one group.
     * Every pair of strings in the same group are equivalent.
     * Return an integer denoting the minimum number of groups.
     *
     * Input: words = ["ntgwz","zwntg"]
     * Output: 1
     *
     * Input: words = ["abc","cab","bac","acb","bca","cba"]
     * Output: 3
     *
     * Input: words = ["leet","abb","bab","deed","edde","code","bba"]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= words.length <= 10^5
     * 1 <= words[i].length <= 5 * 10^5
     * The sum of words[i].length does not exceed 5 * 10^5.
     * words[i] consist of lowercase English letters.
     * @param words
     * @return
     */
    // time = O(L), space = O(L)  L: sum{words}
    public int minimumGroups(String[] words) {
        HashSet<String> set = new HashSet<>();
        for (String w : words) {
            int m = w.length();
            char[] mins = new char[m];

            char[] even = new char[(m + 1) / 2];
            for (int i = 0; i < even.length; i++) even[i] = w.charAt(i * 2);

            char[] s = smallestRepresentation(even);
            for (int i = 0; i < s.length; i++) mins[i * 2] = s[i];

            char[] odd = new char[m / 2];
            for (int i = 0; i < odd.length; i++) odd[i] = w.charAt(i * 2 + 1);
            s = smallestRepresentation(odd);
            for (int i = 0; i < s.length; i++) mins[i * 2 + 1] = s[i];

            set.add(new String(mins));
        }
        return set.size();
    }

    // 返回 str 的字典序最小的循环同构串
    private char[] smallestRepresentation(char[] s) {
        int n = s.length;
        char[] t = new char[n * 2]; // t = s + s
        System.arraycopy(s, 0, t, 0, n);
        System.arraycopy(s, 0, t, n, n);

        int i = 0;
        int j = 1;
        while (j < n) {
            // 暴力比较：是 i 开头的字典序小，还是 j 开头的字典序小？
            // 相同就继续往后比，至多循环 n 次（如果循环 n 次，说明所有字母都相同，不用再比了）
            int k = 0;
            while (k < n && t[i + k] == t[j + k]) k++;
            if (k >= n) break;
            // 比如从 i 开始是 "aaab"，从 j 开始是 "aaac"
            // 从 i 开始比从 j 开始更小（排除 j）
            // 此外：
            // 从 i+1 开始比从 j+1 开始更小，所以从 j+1 开始不可能是答案，排除
            // 从 i+2 开始比从 j+2 开始更小，所以从 j+2 开始不可能是答案，排除
            // ……
            // 从 i+k 开始比从 j+k 开始更小，所以从 j+k 开始不可能是答案，排除
            // 所以下一个「可能是答案」的开始位置是 j+k+1
            if (t[i + k] < t[j + k]) j += k + 1; // 注：如果求字典序最大，改成 >
            else {
                // 从 j 开始比从 i 开始更小，更新 i=j（也意味着我们排除了 i）
                // 此外：
                // 从 j+1 开始比从 i+1 开始更小，所以从 i+1 开始不可能是答案，排除
                // 从 j+2 开始比从 i+2 开始更小，所以从 i+2 开始不可能是答案，排除
                // ……
                // 从 j+k 开始比从 i+k 开始更小，所以从 i+k 开始不可能是答案，排除
                // 所以把 j 跳到 i+k+1，不过这可能比 j+1 小，所以与 j+1 取 max
                // 综上所述，下一个「可能是答案」的开始位置是 max(j+1, i+k+1)
                int tmp = j;
                j = Math.max(j, i + k) + 1;
                i = tmp;
            }
            // 每次要么排除 k+1 个与 i 相关的位置（这样的位置至多 n 个），要么排除 k+1 个与 j 相关的位置（这样的位置至多 n 个）
            // 所以上面关于 k 的循环，∑k <= 2n，所以二重循环的总循环次数是 O(n) 的
        }
        return Arrays.copyOfRange(t, i, i + n);
    }
}
/**
 * 最小表示法 + 哈希集合去重
 * 两个字符串等价：
 * 1. 偶数下标的子序列循环同构。
 * 2. 奇数下标的子序列循环同构。
 * 为了判断多个字符串是否等价，我们可以把每个字符串的偶数下标子序列 a 变成其「最小表示」：在 a 的所有循环同构字符串中，字典序最小的字符串。
 * 奇数下标子序列同理。
 * 然后把得到的字符串插入哈希集合中。
 * 最后，哈希集合的大小，即为最小组数。
 *
 * 在长为 2n 的字符串中，找到一个长为 n 的字典序最小的子串。
 */