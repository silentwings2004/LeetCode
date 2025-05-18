package LC1201_1500;

public class LC1401_CircleandRectangleOverlapping {
    /**
     * You are given a circle represented as (radius, xCenter, yCenter) and an axis-aligned rectangle represented as
     * (x1, y1, x2, y2), where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the coordinates
     * of the top-right corner of the rectangle.
     *
     * Return true if the circle and rectangle are overlapped otherwise return false. In other words, check if there is
     * any point (xi, yi) that belongs to the circle and the rectangle at the same time.
     *
     * Input: radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
     * Output: true
     *
     * Input: radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
     * Output: false
     *
     * Input: radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
     * Output: true
     *
     * Constraints:
     *
     * 1 <= radius <= 2000
     * -10^4 <= xCenter, yCenter <= 10^4
     * -10^4 <= x1 < x2 <= 10^4
     * -10^4 <= y1 < y2 <= 10^4
     * @param radius
     * @param x_center
     * @param y_center
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        double rx = (x1 + x2) / 2.0, ry = (y1 + y2) / 2.0;
        double hx = (x2 - x1) / 2.0, hy = (y2 - y1) / 2.0;
        double cx = x_center, cy = y_center;
        double vx = Math.abs(rx - cx), vy = Math.abs(ry - cy);
        double ux = Math.max(vx - hx, 0), uy = Math.max(vy - hy, 0);
        return ux * ux + uy * uy <= radius * radius;
   }

   // S2
   // time = O(1), space = O(1)
   public boolean checkOverlap2(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        for (int i = xCenter - radius; i <= xCenter + radius; i++) {
            for (int j = yCenter - radius; j <= yCenter + radius; j++) {
                if (get_dist(i, j, xCenter, yCenter) <= radius * radius && i >= x1 && j >= y1 && i <= x2 && j <= y2) {
                    return true;
                }
            }
        }
        return false;
   }

   private int get_dist(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
   }
}