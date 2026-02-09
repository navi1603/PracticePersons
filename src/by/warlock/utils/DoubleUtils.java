package by.warlock.utils;

public class DoubleUtils {
    public static final double EPSILON_6 = 0.000001D;

    public static boolean equals(double a, double b, double epsilon) {
        return Math.abs(a - b) < epsilon;
    }

    public static boolean equals(double a, double b) {
        return equals(a, b, EPSILON_6);
    }
}
