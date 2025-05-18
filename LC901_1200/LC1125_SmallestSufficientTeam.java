package LC901_1200;
import java.util.*;
public class LC1125_SmallestSufficientTeam {
    /**
     * In a project, you have a list of required skills req_skills, and a list of people. The ith person people[i]
     * contains a list of skills that the person has.
     *
     * Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least
     * one person in the team who has that skill. We can represent these teams by the index of each person.
     *
     * For example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
     * Return any sufficient team of the smallest possible size, represented by the index of each person. You may
     * return the answer in any order.
     *
     * It is guaranteed an answer exists.
     *
     * Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
     * Output: [0,2]
     *
     * Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],
     * ["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
     * Output: [1,2]
     *
     * Constraints:
     *
     * 1 <= req_skills.length <= 16
     * 1 <= req_skills[i].length <= 16
     * req_skills[i] consists of lowercase English letters.
     * All the strings of req_skills are unique.
     * 1 <= people.length <= 60
     * 0 <= people[i].length <= 16
     * 1 <= people[i][j].length <= 16
     * people[i][j] consists of lowercase English letters.
     * All the strings of people[i] are unique.
     * Every skill in people[i] is a skill in req_skills.
     * It is guaranteed a sufficient team exists.
     * @param req_skills
     * @param people
     * @return
     */
    // S1
    // time = O(2^n * m), space = O(2^n * m)
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length, m = people.size();
        int[] f = new int[1 << n], g = new int[m];
        Arrays.fill(f, n + 1);
        f[0] = 0;

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(req_skills[i], i);
        for (int i = 0; i < m; i++) {
            for (String s : people.get(i)) {
                g[i] |= 1 << map.get(s);
            }
        }

        int[][] path = new int[1 << n][2];
        for (int i = 0; i < 1 << n; i++) {
            for (int j = 0; j < m; j++) {
                if (f[i | g[j]] > f[i] + 1) {
                    f[i | g[j]] = f[i] + 1;
                    path[i | g[j]] = new int[]{i, j};
                }
            }
        }

        int[] res = new int[f[(1 << n) - 1]];
        for (int i = (1 << n) - 1, j = 0; i > 0; j++) {
            res[j] = path[i][1];
            i = path[i][0];
        }
        return res;
    }

    // S2: dfs + state compression
    List<Integer> res;
    int m, n, minv;
    public int[] smallestSufficientTeam2(String[] req_skills, List<List<String>> people) {
        HashMap<String, Integer> map = new HashMap<>();
        m = req_skills.length;
        for (int i = 0; i < m; i++) map.put(req_skills[i], i);
        n = people.size();
        int[][] p = new int[n][2];
        for (int i = 0; i < n; i++) {
            int state = 0;
            for (String s : people.get(i)) {
                if (map.containsKey(s)) state |= 1 << map.get(s);
            }
            p[i] = new int[]{state, Integer.bitCount(state), i};
        }

        Arrays.sort(p, (o1, o2) -> o2[1] - o1[1]);
        minv = n;
        res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dfs(p, i, 0, new ArrayList<>());
        }

        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }

    private void dfs(int[][] p, int u, int s, List<Integer> path) {
        if (path.size() >= minv) return;
        if (s == (1 << m) - 1) {
            if (path.size() < minv) {
                minv = path.size();
                res = new ArrayList<>(path);
            }
            return;
        }

        int t = (1 << m) - 1 - s;
        for (int i = u; i < n; i++) {
            if ((t & p[i][0]) == 0) continue;
            int ss = s | p[i][0];
            path.add(p[i][2]);
            dfs(p, i + 1, ss, path);
            path.remove(path.size() - 1);
        }
    }
}
/**
 * 状压dp
 * 状态表示：
 * 1.集合：所有覆盖的技能为i的所有方案的集合
 * 2.属性：min
 * 状态计算：每个点的起点状态很随机，高度不确定，但是每个点能够更新哪些状态，是确定的
 * dp本质就是有向无环图上求最短路或者最长路
 * => 2^16 * 60
 * 按照拓扑序来算
 * A | x >= A
 * 只需要按照数值从小到大来枚举即可
 */