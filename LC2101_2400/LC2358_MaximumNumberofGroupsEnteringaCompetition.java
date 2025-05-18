package LC2101_2400;
import java.util.*;
public class LC2358_MaximumNumberofGroupsEnteringaCompetition {
    /**
     * You are given a positive integer array grades which represents the grades of students in a university. You would
     * like to enter all these students into a competition in ordered non-empty groups, such that the ordering meets the following conditions:
     *
     * The sum of the grades of students in the ith group is less than the sum of the grades of students in the
     * (i + 1)th group, for all groups (except the last).
     * The total number of students in the ith group is less than the total number of students in the (i + 1)th group,
     * for all groups (except the last).
     * Return the maximum number of groups that can be formed.
     *
     * Input: grades = [10,6,12,7,3,5]
     * Output: 3
     *
     * Input: grades = [8,8]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= grades.length <= 10^5
     * 1 <= grades[i] <= 10^5
     * @param grades
     * @return
     */
    // time = O(n), space = O(1)
    public int maximumGroups(int[] grades) {
        int n = grades.length, k = 1;
        while ((k + 1) * (k + 2) / 2 <= n) k++; // 判断第k+1个数是否成立，1 + ... + k + 1
        return k;
    }
}
/**
 * 只要考虑数的个数即可，每一组的人都比前一组大，第一个条件总和不用管 => 1, 2, 3, .. k
 * 找到最大的一个k,使得1 + .. + k <= n
 * k * (k + 1) / 2 <= n
 * 如果有剩余的话，全部加到最后一组即可。
 */