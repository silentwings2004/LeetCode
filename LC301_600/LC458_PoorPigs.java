package LC301_600;

public class LC458_PoorPigs {
    /**
     * There are buckets buckets of liquid, where exactly one of the buckets is poisonous. To figure out which one is
     * poisonous, you feed some number of (poor) pigs the liquid to see whether they will die or not. Unfortunately,
     * you only have minutesToTest minutes to determine which bucket is poisonous.
     *
     * You can feed the pigs according to these steps:
     *
     * Choose some live pigs to feed.
     * For each pig, choose which buckets to feed it. The pig will consume all the chosen buckets simultaneously and
     * will take no time.
     * Wait for minutesToDie minutes. You may not feed any other pigs during this time.
     * After minutesToDie minutes have passed, any pigs that have been fed the poisonous bucket will die, and all others
     * will survive.
     * Repeat this process until you run out of time.
     * Given buckets, minutesToDie, and minutesToTest, return the minimum number of pigs needed to figure out which
     * bucket is poisonous within the allotted time.
     *
     * Input: buckets = 1000, minutesToDie = 15, minutesToTest = 60
     * Output: 5
     *
     * Input: buckets = 4, minutesToDie = 15, minutesToTest = 15
     * Output: 2
     *
     * Input: buckets = 4, minutesToDie = 15, minutesToTest = 30
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= buckets <= 1000
     * 1 <= minutesToDie <= minutesToTest <= 100
     * @param buckets
     * @param minutesToDie
     * @param minutesToTest
     * @return
     */
    // time = O(1), space = O(1)
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int t = minutesToTest / minutesToDie + 1, res = 0;
        while (Math.pow(res, t) < buckets) res++;
        return res;
    }
}
/**
 * 每头猪一共可以喝 p / m 轮
 * x : 1轮后死
 * ....
 * k轮后死
 * 不死
 * 最多可以有(k+1)^x >= n  => x >= log(k+1) n 上取整
 * x位的k进制数
 *
 * 先思考一只猪，在题意的15min/60min的条件下，能鉴别出几桶水？答案是5.
 * 因为这5瓶水对应了这只猪第15分钟死（第一桶有毒）、第30分钟死（第二桶有毒）、第45分钟死（第三桶有毒）、第60分钟死（第四桶有毒）、
 * 不死（前四桶都没毒，根据题意，必然是第五桶有毒）。
 *
 * 既然一只猪能鉴别五桶，那么两只猪就是25桶，为什么？
 * 因为这25桶水可以按照五进制编码两个bit：比如01，表示这瓶水不给A猪喝，但给B猪第一批次喝；
 * 又比如34，表示这瓶水给A猪第三批次喝，给B猪第四批次喝。最后，根据AB两只猪死亡的状态（死或不死、在什么时候死）可以鉴别出有毒的那一桶的编码。
 * 同理引申，那么三只猪就是可以鉴别5^3=125桶。最后，想鉴别1000桶就话，需要log(5)(1000)=5只猪。
 *
 * 2只猪，一个分别喝一行，另一个喝一列，然后根据2只猪的死亡情况，找到交点的那一桶就是有毒的。
 */