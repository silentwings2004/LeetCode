package LC2101_2400;

public class LC2384_LargestPalindromicNumber {
    /**
     * You are given a string num consisting of digits only.
     *
     * Return the largest palindromic integer (in the form of a string) that can be formed using digits taken from num.
     * It should not contain leading zeroes.
     *
     * Notes:
     *
     * You do not need to use all the digits of num, but you must use at least one digit.
     * The digits can be reordered.
     *
     * Input: num = "444947137"
     * Output: "7449447"
     *
     * Input: num = "00009"
     * Output: "9"
     *
     * Constraints:
     *
     * 1 <= num.length <= 10^5
     * num consists of digits.
     * @param num
     * @return
     */
    // time = O(n), space = O(n)
    public String largestPalindromic(String num) {
        int[] cnt = new int[10];
        for (char c : num.toCharArray()) cnt[c - '0']++;

        StringBuilder sb = new StringBuilder();
        int x = -1;
        for (int i = 9; i >= 0; i--) {
            if (cnt[i] == 0) continue;
            if (cnt[i] == 1) {
                if (x == -1) x = i;
                else continue;
            } else {
                for (int j = 0; j < cnt[i] / 2; j++) sb.append(i);
                if (cnt[i] % 2 == 1 && x == -1) x = i;
            }
        }

        if (sb.length() == 0 || sb.charAt(0) == '0') return x == -1 ? "0" : x + "";
        String s = sb.toString();
        String t = sb.reverse().toString();
        return x == -1 ? s + t : s + x + t;
    }

    // S2:
    // time = O(n), space = O(n)
    public String largestPalindromic2(String num) {
        int[] cnt = new int[10];
        for (char c : num.toCharArray()) cnt[c - '0']++;
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i > 0; i--) { // 判断有无非0数出现2次及以上
            if (cnt[i] >= 2) {
                sb.append(i);
                cnt[i] -= 2;
                break;
            }
        }

        if (sb.length() == 0) { // 不存在任何非0数出现2次以上 => 回文数只有1位
            for (int i = 9; i >= 0; i--) { // 只有1位的话是可以有前导0的
                if (cnt[i] > 0) return String.valueOf(i);
            }
        }

        // 否则最高位就有了，只要出现2次，就可以放在首尾
        for (int i = 9; i >= 0; i--) {
            while (cnt[i] >= 2) {
                sb.append(i);
                cnt[i] -= 2;
            }
        }

        String pre = sb.toString();
        String suf = sb.reverse().toString();

        // 枚举下正中间的数
        for (int i = 9; i >= 0; i--) {
            if (cnt[i] > 0) {
                pre += i;
                break;
            }
        }
        return pre + suf;
    }
}
/**
 * 先确定下最高位取什么
 */