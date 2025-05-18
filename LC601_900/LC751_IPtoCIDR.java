package LC601_900;
import java.util.*;
public class LC751_IPtoCIDR {
    /**
     * An IP address is a formatted 32-bit unsigned integer where each group of 8 bits is printed as a decimal number
     * and the dot character '.' splits the groups.
     *
     * For example, the binary number 00001111 10001000 11111111 01101011 (spaces added for clarity) formatted as an IP
     * address would be "15.136.255.107".
     * A CIDR block is a format used to denote a specific set of IP addresses. It is a string consisting of a base IP
     * address, followed by a slash, followed by a prefix length k. The addresses it covers are all the IPs whose first
     * k bits are the same as the base IP address.
     *
     * For example, "123.45.67.89/20" is a CIDR block with a prefix length of 20. Any IP address whose binary
     * representation matches 01111011 00101101 0100xxxx xxxxxxxx, where x can be either 0 or 1, is in the set covered
     * by the CIDR block.
     * You are given a start IP address ip and the number of IP addresses we need to cover n. Your goal is to use as
     * few CIDR blocks as possible to cover all the IP addresses in the inclusive range [ip, ip + n - 1] exactly. No
     * other IP addresses outside of the range should be covered.
     *
     * Return the shortest list of CIDR blocks that covers the range of IP addresses. If there are multiple answers,
     * return any of them.
     *
     * Input: ip = "255.0.0.7", n = 10
     * Output: ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
     *
     * Input: ip = "117.145.102.62", n = 8
     * Output: ["117.145.102.62/31","117.145.102.64/30","117.145.102.68/31"]
     *
     * Constraints:
     *
     * 7 <= ip.length <= 15
     * ip is a valid IPv4 on the form "a.b.c.d" where a, b, c, and d are integers in the range [0, 255].
     * 1 <= n <= 1000
     * Every implied address ip + x (for x < n) will be a valid IPv4 address.
     * @param ip
     * @param n
     * @return
     */
    // time = O(1), space = O(1)
    public List<String> ipToCIDR(String ip, int n) {
        String[] ips = ip.split("\\.");
        long x = 0;
        for (int i = 0; i < ips.length; i++) {
            x = Integer.parseInt(ips[i]) + x * 256; // 将起始ip地址转化为long整数
        }

        List<String> res = new ArrayList<>();
        while (n > 0) {
            long step = lowbit(x); // 从右向左取1作为每个有效CIDR的分割点
            if (step == 0) step = 1 << 10; // 由于n最大是1000，所以0的话一步到位取到超过n的最小的2的整次幂，即2^10 = 1024
            while (step > n) step >>= 1; // 根据n的大小，调整剩余可取ip的数量来确定step的范围
            res.add(longToIp(x, (int)step)); // 转化出有效的CIDR
            x += step; // update 当前的 base ip 对应的long整数
            n -= step; // Update n
        }
        return res;
    }

    private String longToIp(long x, int step) {
        int[] res = new int[4];
        res[0] = (int)(x & 255);x >>= 8;
        res[1] = (int)(x & 255);x >>= 8;
        res[2] = (int)(x & 255);x >>= 8;
        res[3] = (int)x;
        int len = 33;
        while (step > 0) { // 取1位对应是32，所以len要从33开始减
            len--;
            step >>= 1;
        }
        return res[3] + "." + res[2] + "." + res[1] + "." + res[0] + "/" + len;
    }

    private long lowbit(long x) {
        return x & -x;
    }
}