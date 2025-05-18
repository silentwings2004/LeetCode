package LC601_900;
import java.util.*;
public class LC752_OpentheLock {
    /**
     * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5',
     * '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0'
     * to be '9'. Each move consists of turning one wheel one slot.
     *
     * The lock initially starts at '0000', a string representing the state of the 4 wheels.
     *
     * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the
     * lock will stop turning and you will be unable to open it.
     *
     * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number
     * of turns required to open the lock, or -1 if it is impossible.
     *
     * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= deadends.length <= 500
     * deadends[i].length == 4
     * target.length == 4
     * target will not be in the list deadends.
     * target and deadends[i] consist of digits only.
     * @param deadends
     * @param target
     * @return
     */
    // S1
    // time = O(n^2 * a^n + k) = O(16 + 10^4 + k) = O(k), space = O(a^n + k) = O(10^4 + k) = O(k)
    // a表示数字的个数 = 10，n表示状态的位数 = 4，k表示数组deadends的大小
    public int openLock(String[] deadends, String target) {
        String start = "0000";
        if (start.equals(target)) return 0;
        HashSet<String> set = new HashSet<>();
        for (String s : deadends) set.add(s);
        if (set.contains(start)) return -1;

        Queue<String> q = new LinkedList<>();
        q.offer(start);
        HashMap<String, Integer> map = new HashMap<>();
        map.put(start, 0);

        while (!q.isEmpty()) {
            String t = q.poll();
            if (t.equals(target)) return map.get(t);
            for (int i = 0; i < 4; i++) {
                for (int j = -1; j <= 1; j += 2) {
                    char[] s = t.toCharArray();
                    s[i] = (char)((s[i] - '0' + j + 10) % 10 + '0');
                    String str = String.valueOf(s);
                    if (!map.containsKey(str) && !set.contains(str)) {
                        map.put(str, map.get(t) + 1);
                        q.offer(str);
                    }
                }
            }
        }
        return -1;
    }

    // S2
    // time = O(n^2 * a^n + k) = O(16 + 10^4 + k) = O(k), space = O(a^n + k) = O(10^4 + k) = O(k)
    public int openLock2(String[] deadends, String target) {
        HashSet<String> dset = new HashSet<>();
        HashSet<String> vis = new HashSet<>();
        for (String s : deadends) dset.add(s);
        String start = "0000";
        if (dset.contains(start)) return -1;
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        vis.add(start);

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                String t = q.poll();
                if (t.equals(target)) return step;
                char[] s = t.toCharArray();
                for (int i = 0; i < 4; i++) {
                    char c = s[i];
                    // +
                    if (s[i] == '9') s[i] = '0';
                    else s[i]++;
                    String nt= String.valueOf(s);
                    if (vis.add(nt) && !dset.contains(nt)) q.offer(nt);

                    s[i] = c;
                    // -
                    if (s[i] == '0') s[i] = '9';
                    else s[i]--;
                    nt = String.valueOf(s);
                    if (vis.add(nt) && !dset.contains(nt)) q.offer(nt);
                    s[i] = c;
                }
            }
            step++;
        }
        return -1;
    }
}