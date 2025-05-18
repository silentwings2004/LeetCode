package LC3301_3600;

import java.util.PrimitiveIterator;
import java.util.PriorityQueue;

public class LC3399_SmallestSubstringWithIdenticalCharactersII {
    /**
     * You are given a binary string s of length n and an integer numOps.
     *
     * You are allowed to perform the following operation on s at most numOps times:
     *
     * Select any index i (where 0 <= i < n) and flip s[i]. If s[i] == '1', change s[i] to '0' and vice versa.
     * You need to minimize the length of the longest substring of s such that all the characters in the substring are
     * identical.
     *
     * Return the minimum length after the operations.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "000001", numOps = 1
     * Output: 2
     *
     * Input: s = "0000", numOps = 2
     * Output: 1
     *
     * Input: s = "0101", numOps = 0
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n == s.length <= 10^5
     * s consists only of '0' and '1'.
     * 0 <= numOps <= n
     * @param s
     * @param numOps
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int minLength(String s, int numOps) {
        int n = s.length();
        if (check(s, 0, numOps) || check(s, 1, numOps)) return 1;

        int l = 2, r = n;
        while (l < r) {
            int mid = l + r >> 1;
            if (check2(s, numOps, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(String s, int x, int t) {
        int n = s.length(), cnt = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0 && s.charAt(i) - '0' != x || i % 2 == 1 && s.charAt(i) - '0' == x) cnt++;
            if (cnt > t) return false;
        }
        return true;
    }

    private boolean check2(String s, int t, int mid) {
        int n = s.length(), cnt = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;
            int len = j - i;
            cnt += len / (mid + 1);
            if (cnt > t) return false;
            i = j - 1;
        }
        return true;
    }

    // S2: maxHeap
    // time = O(n + numOps * logn), space = O(n)
    public int minLength2(String s, int numOps) {
        int n = s.length(), cnt = 0;
        for (int i = 0; i < n; i++) cnt += (s.charAt(i) ^ i) & 1;
        if (Math.min(cnt, n - cnt) <= numOps) return 1;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        int k = 0;
        for (int i = 0; i < n; i++) {
            k++;
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                pq.offer(new int[]{k, k, 1});
                k = 0;
            }
        }
        while (numOps-- > 0 && pq.peek()[0] > 2) {
            int[] t = pq.poll();
            t[0] = t[1] / ++t[2];
            pq.offer(t);
        }
        return pq.peek()[0];
    }
}
/**
 * 最大堆
 * 1. 切分之后最长的那一段的长度
 * 2. 原始长度
 * 3. 分成多少段
 * 4. 当前这一段是否在中间(不是开头)
 */
