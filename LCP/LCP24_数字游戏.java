package LCP;
import java.util.*;
public class LCP24_数字游戏 {
    /**
     * 小扣在秋日市集入口处发现了一个数字游戏。主办方共有 N 个计数器，计数器编号为 0 ~ N-1。每个计数器上分别显示了一个数字，小扣按计数器编号升序
     * 将所显示的数字记于数组 nums。每个计数器上有两个按钮，分别可以实现将显示数字加一或减一。小扣每一次操作可以选择一个计数器，按下加一或减一按钮。
     *
     * 主办方请小扣回答出一个长度为 N 的数组，第 i 个元素(0 <= i < N)表示将 0~i 号计数器 初始 所示数字操作成满足所有条件
     * nums[a]+1 == nums[a+1],(0 <= a < i) 的最小操作数。回答正确方可进入秋日市集。
     *
     * 由于答案可能很大，请将每个最小操作数对 1,000,000,007 取余。
     *
     * 输入：nums = [3,4,5,1,6,7]
     *
     * 输出：[0,0,0,5,6,7]
     *
     * 输入：nums = [1,2,3,4,5]
     *
     * 输出：[0,0,0,0,0]
     *
     * 输入：nums = [1,1,1,2,3,4]
     *
     * 输出：[0,1,2,3,3,3]
     *
     * 提示：
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^3
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] numsGame(int[] nums) {
        PriorityQueue<Integer> minh = new PriorityQueue<>();
        PriorityQueue<Integer> maxh = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int n = nums.length;
        long mod = (long)(1e9 + 7), ls = 0, rs = 0;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i] - i;
            maxh.offer(x);
            ls += x;
            int y = maxh.peek();
            minh.offer(maxh.poll());
            ls -= y;
            rs += y;
            if (minh.size() - maxh.size() > 1) {
                y = minh.peek();
                maxh.offer(minh.poll());
                rs -= y;
                ls += y;
            }
            if ((i + 1) % 2 == 0) res[i] = (int)((rs - ls) % mod);
            else res[i] = (int)((rs - minh.peek() - ls) % mod);
        }
        return res;
    }
}