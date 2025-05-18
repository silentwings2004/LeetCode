package LC2700_3000;

public class LC2798_NumberofEmployeesWhoMettheTarget {
    /**
     * There are n employees in a company, numbered from 0 to n - 1. Each employee i has worked for hours[i] hours in
     * the company.
     *
     * The company requires each employee to work for at least target hours.
     *
     * You are given a 0-indexed array of non-negative integers hours of length n and a non-negative integer target.
     *
     * Return the integer denoting the number of employees who worked at least target hours.
     *
     * Input: hours = [0,1,2,3,4], target = 2
     * Output: 3
     *
     * Input: hours = [5,1,4,2,2], target = 6
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n == hours.length <= 50
     * 0 <= hours[i], target <= 10^5
     * @param hours
     * @param target
     * @return
     */
    // time = O(n), space = O(1)
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int res = 0;
        for (int x : hours) {
            if (x >= target) res++;
        }
        return res;
    }
}