package LC2101_2400;
import java.util.*;
public class LC2345_FindingtheNumberofVisibleMountains {
    /**
     * You are given a 0-indexed 2D integer array peaks where peaks[i] = [xi, yi] states that mountain i has a peak at
     * coordinates (xi, yi). A mountain can be described as a right-angled isosceles triangle, with its base along the
     * x-axis and a right angle at its peak. More formally, the gradients of ascending and descending the mountain are 1
     * and -1 respectively.
     *
     * A mountain is considered visible if its peak does not lie within another mountain (including the border of other
     * mountains).
     *
     * Return the number of visible mountains.
     *
     * Input: peaks = [[2,2],[6,3],[5,4]]
     * Output: 2
     *
     * Input: peaks = [[1,3],[1,3]]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= peaks.length <= 10^5
     * peaks[i].length == 2
     * 1 <= xi, yi <= 10^5
     * @param peaks
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int visibleMountains(int[][] peaks) {
        Arrays.sort(peaks, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int n = peaks.length, res = 0, cnt = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int x = peaks[i][0], y = peaks[i][1];
            if (i > 0 && peaks[i][0] == peaks[i - 1][0] && peaks[i][1] == peaks[i - 1][1]) {
                if (!stack.isEmpty() && stack.peek() == i - 1) cnt++;
                continue;
            }
            while (!stack.isEmpty() && y - peaks[stack.peek()][1] >= x - peaks[stack.peek()][0]) stack.pop();
            if (!stack.isEmpty() && peaks[stack.peek()][1] - y >= x - peaks[stack.peek()][0]) continue;
            stack.push(i);
        }

        return stack.size() - cnt;
    }
}
