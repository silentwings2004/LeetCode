package LC1501_1800;
import java.util.*;
public class LC1700_NumberofStudentsUnabletoEatLunch {
    /**
     * The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1
     * respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.
     *
     * The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a
     * stack. At each step:
     *
     * If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and
     * leave the queue.
     * Otherwise, they will leave it and go to the queue's end.
     * This continues until none of the queue students want to take the top sandwich and are thus unable to eat.
     *
     * You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the ith sandwich in
     * the stack (i = 0 is the top of the stack) and students[j] is the preference of the jth student in the initial
     * queue (j = 0 is the front of the queue). Return the number of students that are unable to eat.
     *
     * Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
     * Output: 0
     *
     * Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= students.length, sandwiches.length <= 100
     * students.length == sandwiches.length
     * sandwiches[i] is 0 or 1.
     * students[i] is 0 or 1.
     * @param students
     * @param sandwiches
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int countStudents(int[] students, int[] sandwiches) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : students) map.put(x, map.getOrDefault(x, 0) + 1);
        int n = sandwiches.length;
        for (int i = 0; i < n; i++) {
            int x = sandwiches[i];
            if (map.containsKey(x)) {
                map.put(x, map.get(x) - 1);
                if (map.get(x) == 0) map.remove(x);
            } else return n - i;
        }
        return 0;
    }

    // S2
    // time = O(n), space = O(1)
    public int countStudents2(int[] students, int[] sandwiches) {
        int zero = 0, one = 0;
        for (int x : students) {
            if (x == 0) zero++;
            else one++;
        }

        for (int x : sandwiches) {
            if (x == 0) zero--;
            else one--;
            if (zero < 0 || one < 0) return Math.max(one, zero);
        }
        return 0;
    }
}
