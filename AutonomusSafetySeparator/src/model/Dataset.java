package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dataset {
    private final List<LabeledPoint> points;

    public Dataset() {
        this.points = new ArrayList<>();
    }

    public void addPoint(LabeledPoint labeledPoint) {
        points.add(labeledPoint);
    }

    public List<LabeledPoint> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public List<LabeledPoint> getPointsByLabel(int label) {
        List<LabeledPoint> filtered = new ArrayList<>();
        for (LabeledPoint point : points) {
            if (point.getLabel() == label) {
                filtered.add(point);
            }
        }
        return filtered;
    }

    public int size() {
        return points.size();
    }
}