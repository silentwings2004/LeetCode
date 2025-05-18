package LC301_600;

public class LC405_ConvertaNumbertoHexadecimal {
    /**
     * Given an integer num, return a string representing its hexadecimal representation. For negative integers,
     * two’s complement method is used.
     *
     * All the letters in the answer string should be lowercase characters, and there should not be any leading zeros
     * in the answer except for the zero itself.
     *
     * Note: You are not allowed to use any built-in library method to directly solve this problem.
     *
     * Input: num = 26
     * Output: "1a"
     *
     * Input: num = -1
     * Output: "ffffffff"
     *
     * Constraints:
     *
     * -2^31 <= num <= 2^31 - 1
     * @param num
     * @return
     */
    // time = O(1), space = O(1)
    public String toHex(int num) {
        if (num == 0) return "0";
        String hex = "0123456789abcdef";
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(hex.charAt(num & 0xf));
            num >>>= 4; // 无符号右移
        }
        return sb.reverse().toString();
    }
}
/**
 * 分成8组，每组4位  （取4位，删除4位）
 */