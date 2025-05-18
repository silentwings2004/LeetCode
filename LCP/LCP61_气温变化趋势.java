package LCP;

public class LCP61_气温变化趋势 {
    /**
     * 力扣城计划在两地设立「力扣嘉年华」的分会场，气象小组正在分析两地区的气温变化趋势，对于第 i ~ (i+1) 天的气温变化趋势，将根据以下规则判断：
     *
     * 若第 i+1 天的气温 高于 第 i 天，为 上升 趋势
     * 若第 i+1 天的气温 等于 第 i 天，为 平稳 趋势
     * 若第 i+1 天的气温 低于 第 i 天，为 下降 趋势
     * 已知 temperatureA[i] 和 temperatureB[i] 分别表示第 i 天两地区的气温。 组委会希望找到一段天数尽可能多，且两地气温变化趋势相同的时间
     * 举办嘉年华活动。请分析并返回两地气温变化趋势相同的最大连续天数。
     *
     * 即最大的 n，使得第 i~i+n 天之间，两地气温变化趋势相同
     *
     * 输入： temperatureA = [21,18,18,18,31] temperatureB = [34,32,16,16,17]
     * 输出：2
     *
     * 输入： temperatureA = [5,10,16,-6,15,11,3] temperatureB = [16,22,23,23,25,3,-16]
     * 输出：3
     *
     * 提示：
     *
     * 2 <= temperatureA.length == temperatureB.length <= 1000
     * -20 <= temperatureA[i], temperatureB[i] <= 40
     * @param temperatureA
     * @param temperatureB
     * @return
     */
    // time = O(n), space = O(1)
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int n = temperatureA.length, res = 0;
        for (int i = 0; i + 1 < n; i++) {
            if (get(temperatureA, i) == get(temperatureB, i)) {
                int j = i + 1;
                while (j + 1 < n && get(temperatureA, j) == get(temperatureB, j)) j++;
                res = Math.max(res, j - i);
                i = j - 1;
            }
        }
        return res;
    }

    private int get(int[] w, int i) {
        if (w[i + 1] > w[i]) return 1;
        else if (w[i + 1] < w[i]) return -1;
        return 0;
    }
}