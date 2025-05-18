package LC2401_2700;

public class LC2525_CategorizeBoxAccordingtoCriteria {
    /**
     * Given four integers length, width, height, and mass, representing the dimensions and mass of a box, respectively,
     * return a string representing the category of the box.
     *
     * The box is "Bulky" if:
     * Any of the dimensions of the box is greater or equal to 10^4.
     * Or, the volume of the box is greater or equal to 10^9.
     * If the mass of the box is greater or equal to 100, it is "Heavy".
     * If the box is both "Bulky" and "Heavy", then its category is "Both".
     * If the box is neither "Bulky" nor "Heavy", then its category is "Neither".
     * If the box is "Bulky" but not "Heavy", then its category is "Bulky".
     * If the box is "Heavy" but not "Bulky", then its category is "Heavy".
     * Note that the volume of the box is the product of its length, width and height.
     *
     * Input: length = 1000, width = 35, height = 700, mass = 300
     * Output: "Heavy"
     *
     * Input: length = 200, width = 50, height = 800, mass = 50
     * Output: "Neither"
     *
     * Constraints:
     *
     * 1 <= length, width, height <= 10^5
     * 1 <= mass <= 10^3
     * @param length
     * @param width
     * @param height
     * @param mass
     * @return
     */
    // time = O(1), space = O(1)
    public String categorizeBox(int length, int width, int height, int mass) {
        long vol = (long) length * width * height;
        int a = (int) 1e4, b = (int) 1e9;
        if (length >= a || width >= a || height >= a || vol >= b) {
            if (mass >= 100) return "Both";
            return "Bulky";
        } else {
            if (mass >= 100) return "Heavy";
            return "Neither";
        }
    }
}
