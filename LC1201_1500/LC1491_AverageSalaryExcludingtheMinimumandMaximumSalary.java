package LC1201_1500;

public class LC1491_AverageSalaryExcludingtheMinimumandMaximumSalary {
    /**
     * You are given an array of unique integers salary where salary[i] is the salary of the ith employee.
     *
     * Return the average salary of employees excluding the minimum and maximum salary. Answers within 10-5 of the
     * actual answer will be accepted.
     *
     * Input: salary = [4000,3000,1000,2000]
     * Output: 2500.00000
     *
     * Input: salary = [1000,2000,3000]
     * Output: 2000.00000
     *
     * Constraints:
     *
     * 3 <= salary.length <= 100
     * 1000 <= salary[i] <= 10^6
     * All the integers of salary are unique.
     * @param salary
     * @return
     */
    // time = O(n), space = O(1)
    public double average(int[] salary) {
        int s = 0, maxv = salary[0], minv = salary[0];
        for (int x : salary) {
            minv = Math.min(minv, x);
            maxv = Math.max(maxv, x);
            s += x;
        }
        return (s - minv - maxv) * 1.0 / (salary.length - 2);
    }
}