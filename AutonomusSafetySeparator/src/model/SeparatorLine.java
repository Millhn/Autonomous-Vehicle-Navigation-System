package model;

public class SeparatorLine {
    private static final double EPSILON = 1e-9;

    private final double a;
    private final double b;
    private final double c;
    private final double margin;

    public SeparatorLine(double a, double b, double c, double margin) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.margin = margin;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getMargin() {
        return margin;
    }

    public double rawValue(Point2D p) {
        return a * p.getX() + b * p.getY() + c;
    }

    public int predict(Point2D p) {
        double value = rawValue(p);
        if (value >= 0) {
            return 1;
        }
        return -1;
    }

    public double distanceToPoint(Point2D p) {
        double numerator = Math.abs(a * p.getX() + b * p.getY() + c);
        double denominator = Math.sqrt(a * a + b * b);

        if (denominator < EPSILON) {
            return Double.POSITIVE_INFINITY;
        }

        return numerator / denominator;
    }

    public boolean isValid() {
        return Math.sqrt(a * a + b * b) > EPSILON;
    }

    @Override
    public String toString() {
        return String.format("%.4fx + %.4fy + %.4f = 0", a, b, c);
    }
}