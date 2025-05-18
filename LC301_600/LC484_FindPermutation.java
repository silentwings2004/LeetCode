package LC301_600;

public class LC484_FindPermutation {
    /**
     * A permutation perm of n integers of all the integers in the range [1, n] can be represented as a string s of
     * length n - 1 where:
     *
     * s[i] == 'I' if perm[i] < perm[i + 1], and
     * s[i] == 'D' if perm[i] > perm[i + 1].
     * Given a string s, reconstruct the lexicographically smallest permutation perm and return it.
     *
     * Input: s = "I"
     * Output: [1,2]
     *
     * Input: s = "DI"
     * Output: [2,1,3]
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s[i] is either 'I' or 'D'.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int[] findPermutation(String s) {
        int n = s.length();
        int[] nums = new int[n + 1];
        for (int i = 0; i <= n; i++) nums[i] = i + 1;

        for (int i = 0; i < n; i++) {
            int j = i;
            char c = s.charAt(i);
            while (j < n && s.charAt(j) == c) j++;
            int k = j - i;
            if (c == 'D') swap(nums, i, k + 1);
            i = j - 1;
        }
        return nums;
    }

    private void swap(int[] nums, int i, int k) {
        int j = i + k - 1;
        while (i < j) {
            int t = nums[i];
            nums[i++] = nums[j];
            nums[j--] = t;
        }
    }

    // S2
    // time = O(n), space = O(n)
    public int[] findPermutation2(String s) {
        s = "I" + s;
        int n = s.length();

        int max = 0, idx = 0;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && s.charAt(j) == 'D') j++;
            int count = j - i;
            for (int k = max + count; k >= max + 1; k--) res[idx++] = k;
            max += count;
            i = j - 1;
        }
        return res;
    }
}
/**
 * same as LC2375
 */