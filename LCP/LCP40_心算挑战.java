package LCP;
import java.util.*;
public class LCP40_心算挑战 {
    /**
     * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt
     * 张卡牌数字总和。 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。
     * 若不存在获取有效得分的卡牌方案，则返回 0。
     *
     * 输入：cards = [1,2,8,9], cnt = 3
     * 输出：18
     *
     * 输入：cards = [3,3,1], cnt = 1
     * 输出：0
     *
     * 提示：
     *
     * 1 <= cnt <= cards.length <= 10^5
     * 1 <= cards[i] <= 1000
     * @param cards
     * @param cnt
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maxmiumScore(int[] cards, int cnt) {
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        for (int x : cards) {
            if (x % 2 == 1) odd.add(x);
            else even.add(x);
        }

        Collections.sort(odd, (o1, o2) -> o2 - o1);
        Collections.sort(even, (o1, o2) -> o2 - o1);

        int m = odd.size(), n = even.size();
        int[] s1 = new int[m + 1], s2 = new int[n + 1];
        for (int i = 1; i <= m; i++) s1[i] = s1[i - 1] + odd.get(i - 1);
        for (int i = 1; i <= n; i++) s2[i] = s2[i - 1] + even.get(i - 1);

        int ans = 0;
        for (int i = 0; i <= cnt && i <= m; i += 2) {
            if (n < cnt - i) continue;
            ans = Math.max(ans, s1[i] + s2[cnt - i]);
        }
        return ans;
    }
}