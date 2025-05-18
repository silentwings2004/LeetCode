package LC601_900;
import java.util.*;
public class LC855_ExamRoom {
    /**
     * There is an exam room with n seats in a single row labeled from 0 to n - 1.
     *
     * When a student enters the room, they must sit in the seat that maximizes the distance to the closest person. If
     * there are multiple such seats, they sit in the seat with the lowest number. If no one is in the room, then the
     * student sits at seat number 0.
     *
     * Design a class that simulates the mentioned exam room.
     *
     * Implement the ExamRoom class:
     *
     * ExamRoom(int n) Initializes the object of the exam room with the number of the seats n.
     * int seat() Returns the label of the seat at which the next student will set.
     * void leave(int p) Indicates that the student sitting at seat p will leave the room. It is guaranteed that there
     * will be a student sitting at seat p.
     *
     * Input
     * ["ExamRoom", "seat", "seat", "seat", "seat", "leave", "seat"]
     * [[10], [], [], [], [], [4], []]
     * Output
     * [null, 0, 9, 4, 2, null, 5]
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * It is guaranteed that there is a student sitting at seat p.
     * At most 104 calls will be made to seat and leave.
     * @param n
     */
    TreeSet<Integer> set;
    int n;
    public LC855_ExamRoom(int n) {
        set = new TreeSet<>();
        this.n = n;
    }
    // time = O(nlogn), space = O(n)
    public int seat() {
        int res = 0;
        if (set.size() > 0) {
            int last = -1, max = set.first();
            for (int x : set) {
                if (last != -1) {
                    int t = (x - last) / 2;
                    if (t > max) {
                        max = t;
                        res = last + t;
                    }
                }
                last = x;
            }
            if (n - 1 - set.last() > max) res = n - 1;
        }
        set.add(res);
        return res;
    }
    // time = O(logn), space = O(n)
    public void leave(int p) {
        set.remove(p);
    }
}