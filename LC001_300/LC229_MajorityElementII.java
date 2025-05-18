package LC001_300;
import java.util.*;
public class LC229_MajorityElementII {
    /**
     * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
     *
     * Follow-up: Could you solve the problem in linear time and in O(1) space?
     *
     * Input: nums = [3,2,3]
     * Output: [3]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^4
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public List<Integer> majorityElement(int[] nums) {
        int r1 = 0, r2 = 0, c1 = 0, c2 = 0, n = nums.length;
        for (int x : nums) {
            if (c1 > 0 && x == r1) c1++;
            else if (c2 > 0 && x == r2) c2++;
            else if (c1 == 0) {
                r1 = x;
                c1++;
            } else if (c2 == 0) {
                r2 = x;
                c2++;
            } else {
                c1--;
                c2--;
            }
        }

        c1 = 0;
        c2 = 0;
        for (int x : nums) {
            if (x == r1) c1++;
            else if (x == r2) c2++;
        }
        List<Integer> res = new ArrayList<>();
        if (c1 > n / 3) res.add(r1);
        if (c2 > n / 3) res.add(r2);
        return res;
    }

    // S2: 扩展到k的情况
    // time = O(n * k), space = O(k)
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int k = 3;
        int[] r = new int[k - 1], c = new int[k - 1];

        for (int x : nums) {
            boolean flag = false;
            for (int i = 0; i < k - 1; i++) {
                if (c[i] > 0 && x == r[i]) {
                    c[i]++;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                for (int i = 0; i < k - 1; i++) {
                    if (c[i] == 0) {
                        r[i] = x;
                        c[i]++;
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                for (int i = 0; i < k - 1; i++) c[i]--;
            }
        }

        Arrays.fill(c, 0);
        for (int x : nums) {
            for (int i = 0; i < k - 1; i++) {
                if (r[i] == x) {
                    c[i]++;
                    break;
                }
            }
        }
        int n = nums.length;
        for (int i = 0; i < k - 1; i++) {
            if (c[i] > n / k) res.add(r[i]);
        }
        return res;
    }
}
/**
 * 摩尔投票法
 * r, c
 * 扩展到k, 开k - 1个仓库即可
 * 消耗x0，必定要k-1个元素与它陪葬
 */