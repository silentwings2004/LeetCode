package LC001_300;
import java.util.*;
public class LC278_FirstBadVersion {
    /**
     * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
     * which causes all the following ones to be bad.
     * <p>
     * You are given an API bool isBadVersion(version) which returns whether version is bad.
     * <p>
     * Input: n = 5, bad = 4
     * Output: 4
     * <p>
     * Constraints:
     * 1 <= bad <= n <= 231 - 1
     *
     * @param n
     * @return
     */
// time = O(logn), space = O(1)
    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int l = 1, r = n;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (isBadVersion(mid)) r = mid;
                else l = mid + 1;
            }
            return r;
        }
    }
}