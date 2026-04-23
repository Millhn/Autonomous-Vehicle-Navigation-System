package model;

public class LabeledPoint {
    private final Point2D point;
    private final int label; // +1 veya -1

    public LabeledPoint(Point2D point, int label) {
        if (label != 1 && label != -1) {
            throw new IllegalArgumentException("Etiket sadece +1 veya -1 olabilir.");
        }
        this.point = point;
        this.label = label;
    }

    public Point2D getPoint() {
        return point;
    }

    public int getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return String.format("%s -> Sinif %d", point, label);
    }
}