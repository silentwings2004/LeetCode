package LC1801_2100;
import java.util.*;
public class LC2092_FindAllPeopleWithSecret {
    /**
     * You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a
     * 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi
     * have a meeting at timei. A person may attend multiple meetings at the same time. Finally, you are given an
     * integer firstPerson.
     *
     * Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then
     * shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if
     * a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.
     *
     * The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in
     * other meetings within the same time frame.
     *
     * Return a list of all the people that have the secret after all the meetings have taken place. You may return the
     * answer in any order.
     *
     * Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
     * Output: [0,1,2,3,5]
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * 1 <= meetings.length <= 10^5
     * meetings[i].length == 3
     * 0 <= xi, yi <= n - 1
     * xi != yi
     * 1 <= timei <= 10^5
     * 1 <= firstPerson <= n - 1
     * @param n
     * @param meetings
     * @param firstPerson
     * @return
     */
    // S1: Union Find
    // time = O((mlogm + n), space = O(m + n)
    int[] p;
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        p[firstPerson] = 0;

        Arrays.sort(meetings, (o1, o2) -> o1[2] - o2[2]);
        int m = meetings.length;
        for (int i = 0; i < m; i++) {
            HashSet<Integer> set = new HashSet<>();
            int a = meetings[i][0], b = meetings[i][1], ts = meetings[i][2];
            p[find(a)] = find(b);
            set.add(a);
            set.add(b);
            int j = i + 1;
            while (j < m && meetings[j][2] == ts) {
                a = meetings[j][0];
                b = meetings[j][1];
                p[find(a)] = find(b);
                set.add(a);
                set.add(b);
                j++;
            }
            for (int x : set) {
                if (find(x) != find(0)) p[x] = x;
            }
            i = j - 1;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (find(i) == find(0)) res.add(i);
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
/**
 * 同一个时刻允许有多个meeting，并且可以有同一个人
 * 同一个时刻里的会议是可以瞬时传递的
 * 把meeting按照时间顺序排列下
 * Set{...}
 * meeting {a, b}
 * meeting {a, c}
 * meeting {b, d}
 * meeting时刻相同，会有些麻烦
 * a,c知道秘密后不得不往回看，不太好处理
 * 先处理有知情者参加的meeting，然后在剩下的里面找有传播者的meeting => 拓扑排序？写好不容易
 * 更直观的写法就是Union Find，有传导性的算法
 * {a,b,c,d} -> 只要有一个在set里面，那么整个都会知情
 * 知情者祖先都为0 -> root都指向0
 * meeting {x, y}
 * {x, y} => {x}, {y} 拆开来
 * meeting {x, a} -> x变成知情者，由于之前x,y union在一起了，y也知情了？不对，因为是上个回合知情的，所以为了避免这个，必须把x,y拆开来。
 * 我们只希望知情者union起来，不知情的Union后要拆开来。
 */