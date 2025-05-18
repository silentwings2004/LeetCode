package LC2401_2700;
import java.util.*;
public class LC2532_TimetoCrossaBridge {
    /**
     * There are k workers who want to move n boxes from an old warehouse to a new one. You are given the two integers
     * n and k, and a 2D integer array time of size k x 4 where time[i] = [leftToRighti, pickOldi, rightToLefti, putNewi].
     *
     * The warehouses are separated by a river and connected by a bridge. The old warehouse is on the right bank of
     * the river, and the new warehouse is on the left bank of the river. Initially, all k workers are waiting on the
     * left side of the bridge. To move the boxes, the ith worker (0-indexed) can :
     *
     * Cross the bridge from the left bank (new warehouse) to the right bank (old warehouse) in leftToRighti minutes.
     * Pick a box from the old warehouse and return to the bridge in pickOldi minutes. Different workers can pick up
     * their boxes simultaneously.
     * Cross the bridge from the right bank (old warehouse) to the left bank (new warehouse) in rightToLefti minutes.
     * Put the box in the new warehouse and return to the bridge in putNewi minutes. Different workers can put their
     * boxes simultaneously.
     * A worker i is less efficient than a worker j if either condition is met:
     *
     * leftToRighti + rightToLefti > leftToRightj + rightToLeftj
     * leftToRighti + rightToLefti == leftToRightj + rightToLeftj and i > j
     * The following rules regulate the movement of the workers through the bridge :
     *
     * If a worker x reaches the bridge while another worker y is crossing the bridge, x waits at their side of the
     * bridge.
     * If the bridge is free, the worker waiting on the right side of the bridge gets to cross the bridge. If more than
     * one worker is waiting on the right side, the one with the lowest efficiency crosses first.
     * If the bridge is free and no worker is waiting on the right side, and at least one box remains at the old
     * warehouse, the worker on the left side of the river gets to cross the bridge. If more than one worker is waiting
     * on the left side, the one with the lowest efficiency crosses first.
     * Return the instance of time at which the last worker reaches the left bank of the river after all n boxes have
     * been put in the new warehouse.
     *
     * Input: n = 1, k = 3, time = [[1,1,2,1],[1,1,3,1],[1,1,4,1]]
     * Output: 6
     *
     * Input: n = 3, k = 2, time = [[1,9,1,8],[10,10,10,10]]
     * Output: 50
     *
     * Constraints:
     *
     * 1 <= n, k <= 10^4
     * time.length == k
     * time[i].length == 4
     * 1 <= leftToRighti, pickOldi, rightToLefti, putNewi <= 1000
     * @param n
     * @param k
     * @param time
     * @return
     */
    // S1
    // time = O(nlogk), space = O(k)
    public int findCrossingTime(int n, int k, int[][] time) {
        PriorityQueue<int[]> pq1 = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]); // {time, 0:l / 1:r, i, time}
        PriorityQueue<int[]> pq2 = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] != o2[1]) return o2[1] - o1[1]; // 先右后左
            if (o1[3] + o1[5] != o2[3] + o2[5]) return (o2[3] + o2[5]) - (o1[3] + o1[5]); // 效率低的优先
            if (o1[2] != o2[2]) return o2[2] - o1[2]; // 最后是按照id大的优先
            return 0;
        });

        for (int i = 0; i < k; i++) { // 在一开始时间点0，所有工人都在左岸等待过桥，所以全部加入过桥队列pq1
            pq1.offer(new int[]{0, 0, i, time[i][0], time[i][1], time[i][2], time[i][3]});
        }

        int t = 0; // 设置初始时间点0
        int nr = n; // nr: 右边老仓库里还剩多少货; n: 还剩多少货没运回新仓库
        while (true) {
            if (pq2.isEmpty() && pq1.peek()[0] > t) t = pq1.peek()[0]; // 所有人都过桥了，且当前没人在岸边等候，需要更新下一个最早的过桥时间点
            while (!pq1.isEmpty() && pq1.peek()[0] <= t) pq2.offer(pq1.poll()); // 当前已经在岸边等着过桥的人入队
            int[] cur = pq2.poll();
            if (cur[1] == 0 && nr == 0) continue; // 右边没货了，左边不用再过来了
            int nxt = 0; //
            if (cur[1] == 0) { // 来自左岸
                nr--; // 搬走右边老仓库的一个货物
                cur[1] = 1; // 准备开始转向，来自右岸，朝向左岸
                nxt = t + cur[3]; // 刚到达右岸的时间点
                cur[0] = t + cur[3] + cur[4]; // 准备朝向左岸回程的时间点
            } else {
                n--; // 运达新仓库一件货，目标数-1
                if (n == 0) return t + cur[5]; // 全部货物运达左岸新仓库，返回最终时间点 = t + 跨过桥刚抵达左岸的时间点
                cur[1] = 0; // 到达左岸，切换方向朝向右岸
                nxt = t + cur[5]; // 刚到达左岸的时间点
                cur[0] = t + cur[5] + cur[6]; // 站回到左岸，准备继续朝右岸出发的时间点
            }
            t = nxt;
            pq1.offer(cur); // 把当前准备过桥的工人加入过桥优先队列pq1
        }
    }

    // S2: Simulation
    public int findCrossingTime2(int n, int k, int[][] time) {
        PriorityQueue<int[]> leftWait = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]); // {efficiency, id}
        PriorityQueue<int[]> rightWait = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        PriorityQueue<int[]> leftArrival = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]); // {arriveTime, id}
        PriorityQueue<int[]> rightArrival = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

        int nextFree = 0;
        for (int i = 0; i < k; i++) leftArrival.offer(new int[]{0, i});

        int returned = 0, res = 0, crossed = 0;
        while (returned < n) {
            if (crossed == n) {
                leftWait.clear();;
                leftArrival.clear();;
            }

            while (!leftArrival.isEmpty() && leftArrival.peek()[0] <= nextFree) {
                int[] t = leftArrival.poll();
                int id = t[1];
                leftWait.offer(new int[]{time[id][0] + time[id][2], id});
            }
            while (!rightArrival.isEmpty() && rightArrival.peek()[0] <= nextFree) {
                int[] t = rightArrival.poll();
                int id = t[1];
                rightWait.offer(new int[]{time[id][0] + time[id][2], id});
            }

            if (leftWait.isEmpty() && rightWait.isEmpty()) {
                int t1 = leftArrival.isEmpty() ? Integer.MAX_VALUE : leftArrival.peek()[0];
                int t2 = rightArrival.isEmpty() ? Integer.MAX_VALUE : rightArrival.peek()[0];
                nextFree = Math.min(t1, t2);
            }

            if (!rightWait.isEmpty()) { // R -> L
                int[] t = rightWait.poll();
                int id = t[1];
                nextFree += time[id][2];
                leftArrival.offer(new int[]{nextFree + time[id][3], id});
                returned++;
                res = Math.max(res, nextFree);
            } else if (!leftWait.isEmpty()) {
                int[] t = leftWait.poll();
                int id = t[1];
                nextFree += time[id][0];
                rightArrival.offer(new int[]{nextFree + time[id][1], id});
                crossed++;
            }
        }
        return res;
    }
}
/**
 * 不需要做决策，过程都是确定的 => 模拟题
 * 把这个过程给模拟出来，谁先走和谁后走都是确定的
 * 切入点：桥两端的等位系统
 * 到达对岸后，又会在一个预期的时间内加入对岸的队列
 * leftWait:{id, efficiency}
 * rightWait:{id, efficiency}
 * nextFreeTime
 * leftArrival: {id, arriveTime}
 * rightArrival: {id, arriveTime}
 */
