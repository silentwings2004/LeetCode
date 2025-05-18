package LC3301_3600;
import java.util.*;
public class LC3450_MaximumStudentsonaSingleBench {
    /**
     * You are given a 2D integer array of student data students, where students[i] = [student_id, bench_id] represents
     * that student student_id is sitting on the bench bench_id.
     *
     * Return the maximum number of unique students sitting on any single bench. If no students are present, return 0.
     *
     * Note: A student can appear multiple times on the same bench in the input, but they should be counted only once
     * per bench.
     *
     * Input: students = [[1,2],[2,2],[3,3],[1,3],[2,3]]
     * Output: 3
     *
     * Input: students = [[1,1],[2,1],[3,1],[4,2],[5,2]]
     * Output: 3
     *
     * Input: students = [[1,1],[1,1]]
     * Output: 1
     *
     * Input: students = []
     * Output: 0
     *
     * Constraints:
     *
     * 0 <= students.length <= 100
     * students[i] = [student_id, bench_id]
     * 1 <= student_id <= 100
     * 1 <= bench_id <= 100
     * @param students
     * @return
     */
    // time = O(n), space = O(n)
    public int maxStudentsOnBench(int[][] students) {
        HashSet<Integer>[] sets = new HashSet[110];
        for (int i = 0; i < 110; i++) sets[i] = new HashSet<>();
        int res = 0;
        for (int[] x : students) {
            int a = x[0], b = x[1];
            sets[b].add(a);
            res = Math.max(res, sets[b].size());
        }
        return res;
    }
}