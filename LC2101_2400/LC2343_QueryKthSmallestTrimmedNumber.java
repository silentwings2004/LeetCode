package LC2101_2400;
import java.util.*;
public class LC2343_QueryKthSmallestTrimmedNumber {
    /**
     * You are given a 0-indexed array of strings nums, where each string is of equal length and consists of only digits.
     *
     * You are also given a 0-indexed 2D integer array queries where queries[i] = [ki, trimi]. For each queries[i], you
     * need to:
     *
     * Trim each number in nums to its rightmost trimi digits.
     * Determine the index of the kith smallest trimmed number in nums. If two trimmed numbers are equal, the number
     * with the lower index is considered to be smaller.
     * Reset each number in nums to its original length.
     * Return an array answer of the same length as queries, where answer[i] is the answer to the ith query.
     *
     * Note:
     *
     * To trim to the rightmost x digits means to keep removing the leftmost digit, until only x digits remain.
     * Strings in nums may contain leading zeros.
     *
     * Input: nums = ["102","473","251","814"], queries = [[1,1],[2,3],[4,2],[1,2]]
     * Output: [2,2,1,0]
     *
     * Input: nums = ["24","37","96","04"], queries = [[2,1],[2,2]]
     * Output: [3,0]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i].length <= 100
     * nums[i] consists of only digits.
     * All nums[i].length are equal.
     * 1 <= queries.length <= 100
     * queries[i].length == 2
     * 1 <= ki <= nums.length
     * 1 <= trimi <= nums[i].length
     * @param nums
     * @param queries
     * @return
     */
    // time = O(m * nlogn), space = O(n)
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        String[][] strs = new String[n][2];
        for (int i = 0; i < n; i++) strs[i] = new String[]{nums[i], i + ""};

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int k = queries[i][0], trim = queries[i][1];
            Arrays.sort(strs, new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    for (int i = o1[0].length() - trim; i < o1[0].length(); i++) {
                        if (o1[0].charAt(i) < o2[0].charAt(i)) return -1;
                        else if (o1[0].charAt(i) > o2[0].charAt(i)) return 1;
                    }
                    return Integer.valueOf(o1[1]) < Integer.valueOf(o2[1]) ? -1 : 1;
                }
            });
            res[i] = Integer.valueOf(strs[k - 1][1]);
        }
        return res;
    }
}
