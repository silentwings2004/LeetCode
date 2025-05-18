package LC601_900;
import java.util.*;
public class LC690_EmployeeImportance {
    /**
     * You have a data structure of employee information, including the employee's unique ID, importance value, and
     * direct subordinates' IDs.
     *
     * You are given an array of employees employees where:
     *
     * employees[i].id is the ID of the ith employee.
     * employees[i].importance is the importance value of the ith employee.
     * employees[i].subordinates is a list of the IDs of the direct subordinates of the ith employee.
     * Given an integer id that represents an employee's ID, return the total importance value of this employee and all
     * their direct and indirect subordinates.
     *
     * Input: employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
     * Output: 11
     *
     * Input: employees = [[1,2,[5]],[5,-3,[]]], id = 5
     * Output: -3
     *
     * Constraints:
     *
     * 1 <= employees.length <= 2000
     * 1 <= employees[i].id <= 2000
     * All employees[i].id are unique.
     * -100 <= employees[i].importance <= 100
     * One employee has at most one direct leader and may have several subordinates.
     * The IDs in employees[i].subordinates are valid IDs.
     * @param employees
     * @param id
     * @return
     */
    // time = O(n), space = O(n)
    HashMap<Integer, Employee> map;
    public int getImportance(List<Employee> employees, int id) {
        map = new HashMap<>();
        for (Employee x : employees) map.put(x.id, x);
        return dfs(id);
    }

    private int dfs(int id) {
        Employee p = map.get(id);
        int res = p.importance;
        for (int next : p.subordinates) res += dfs(next);
        return res;
    }

    // Definition for Employee.
    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }
}