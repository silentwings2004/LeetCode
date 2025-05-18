package LC301_600;
import java.util.*;
public class LC391_PerfectRectangle {
    /**
     * Given an array rectangles where rectangles[i] = [xi, yi, ai, bi] represents an axis-aligned rectangle. The
     * bottom-left point of the rectangle is (xi, yi) and the top-right point of it is (ai, bi).
     *
     * Return true if all the rectangles together form an exact cover of a rectangular region.
     *
     * Input: rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
     * Output: true
     *
     * Input: rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
     * Output: false
     *
     * Input: rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= rectangles.length <= 2 * 10^4
     * rectangles[i].length == 4
     * -10^5 <= xi, yi, ai, bi <= 10^5
     * @param rectangles
     * @return
     */
    // time = O(n), space = O(n)
    public boolean isRectangleCover(int[][] rectangles) {
        HashMap<String, Integer> map = new HashMap<>();
        long sum = 0;
        for (int[] r : rectangles) {
            int a = r[0], b = r[1], c = r[2], d = r[3];
            String k1 = a + "#" + b;
            String k2 = a + "#" + d;
            String k3 = c + "#" + b;
            String k4 = c + "#" + d;
            map.put(k1, map.getOrDefault(k1, 0) + 1);
            map.put(k2, map.getOrDefault(k2, 0) + 1);
            map.put(k3, map.getOrDefault(k3, 0) + 1);
            map.put(k4, map.getOrDefault(k4, 0) + 1);
            sum += (long)(c - a) * (d - b);
        }

        List<int[]> res = new ArrayList<>();
        for (String k : map.keySet()) {
            int cnt = map.get(k);
            if (cnt == 1) {
                String[] str = k.split("#");
                int x = Integer.parseInt(str[0]);
                int y = Integer.parseInt(str[1]);
                res.add(new int[]{x, y});
            } else if (cnt == 3 || cnt > 4) return false;
        }
        if (res.size() != 4) return false;
        Collections.sort(res, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        return (long)(res.get(3)[0] - res.get(0)[0]) * (res.get(3)[1] - res.get(0)[1]) == sum;
    }
}
/**
 * 必要条件：
 * 1. 只出现1次的点有4个
 * 2. 3次： 0个
 * 3. 2次或4次：无限制
 * 4. 总面积相同
 *
 * 是否充分：
 * 4角形
 */