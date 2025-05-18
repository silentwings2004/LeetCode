package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: OpentheLock
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 752. Open the Lock
 */
public class LC752_OpentheLock {
    /**
     * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5',
     * '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0'
     * to be '9'. Each move consists of turning one wheel one slot.
     * <p>
     * The lock initially starts at '0000', a string representing the state of the 4 wheels.
     * <p>
     * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the
     * lock will stop turning and you will be unable to open it.
     * <p>
     * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number
     * of turns required to open the lock, or -1 if it is impossible.
     * <p>
     * Example 1:
     * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     * Output: 6
     * Explanation:
     * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
     * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
     * because the wheels of the lock become stuck after the display becomes the dead end "0102".
     * Example 2:
     * Input: deadends = ["8888"], target = "0009"
     * Output: 1
     * Explanation:
     * We can turn the last wheel in reverse to move from "0000" -> "0009".
     * Example 3:
     * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
     * Output: -1
     * Explanation:
     * We can't reach the target without getting stuck.
     * Example 4:
     * Input: deadends = ["0000"], target = "8888"
     * Output: -1
     * Note:
     * The length of deadends will be in the range [1, 500].
     * target will not be in the list deadends.
     * Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000'
     * to '9999'.
     *
     * @param deadends
     * @param target
     * @return
     */
    // S1：BFS
    // time = O(n^2 * a^n + k), a表示数字的个数，n表示状态的位数，k表示数组deadends的大小
    // space = O(a^n + k) 用来存储队列以及 deadends的集合。
    public int openLock(String[] deadends, String target) {
        // corner case
        if (target == null || target.length() == 0) return -1;

        HashSet<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        if (deadSet.contains("0000")) return -1;
        HashSet<String> visited = new HashSet<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");
        int minLen = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                List<String> nexts = convert(cur, deadSet, visited);
                for (String next : nexts) {
                    if (next.equals((target))) return minLen + 1;
                    queue.offer(next);
                    visited.add(next);
                }
            }
            minLen++;
        }
        return -1;
    }

    private List<String> convert(String cur, HashSet<String> deadSet, HashSet<String> visited) {
        List<String> res = new ArrayList<>();
        char[] chars = cur.toCharArray();
        int len = chars.length;

        for (int i = 0; i < len; i++) {
            char temp = chars[i];
            if (temp == '0') { // -
                chars[i] = '9';
            } else {
                chars[i] = (char)(temp - 1);
            }
            String str = String.valueOf(chars);
            if (!deadSet.contains(str) && !visited.contains(str)) res.add(str);

            if (temp == '9') { // +
                chars[i] = '0';
            } else {
                chars[i] = (char)(temp + 1);
            }
            str = String.valueOf(chars);
            if (!deadSet.contains(str) && !visited.contains(str)) res.add(str);
            chars[i] = temp;
        }
        return res;
    }
}