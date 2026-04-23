package solver;

import model.Dataset;
import model.LabeledPoint;
import model.Point2D;
import model.SeparatorLine;

import java.util.ArrayList;
import java.util.List;

public class MaximumMarginSeparatorSolver {
    private static final double EPSILON = 1e-9;

    public SeparatorLine findBestSeparator(Dataset dataset) {
        List<LabeledPoint> positivePoints = dataset.getPointsByLabel(1);
        List<LabeledPoint> negativePoints = dataset.getPointsByLabel(-1);

        SeparatorLine bestLine = null;
        double bestMargin = -1.0;

        for (LabeledPoint positive : positivePoints) {
            for (LabeledPoint negative : negativePoints) {
                SeparatorLine candidate = buildPerpendicularBisector(
                        positive.getPoint(),
                        negative.getPoint()
                );

                if (candidate == null || !candidate.isValid()) {
                    continue;
                }

                if (separatesAllPoints(candidate, dataset)) {
                    double margin = calculateMinimumMargin(candidate, dataset);
                    SeparatorLine improved = new SeparatorLine(
                            candidate.getA(),
                            candidate.getB(),
                            candidate.getC(),
                            margin
                    );

                    if (margin > bestMargin) {
                        bestMargin = margin;
                        bestLine = improved;
                    }
                }

                SeparatorLine reversed = new SeparatorLine(
                        -candidate.getA(),
                        -candidate.getB(),
                        -candidate.getC(),
                        0.0
                );

                if (separatesAllPoints(reversed, dataset)) {
                    double margin = calculateMinimumMargin(reversed, dataset);
                    SeparatorLine improved = new SeparatorLine(
                            reversed.getA(),
                            reversed.getB(),
                            reversed.getC(),
                            margin
                    );

                    if (margin > bestMargin) {
                        bestMargin = margin;
                        bestLine = improved;
                    }
                }
            }
        }

        return bestLine;
    }

    private SeparatorLine buildPerpendicularBisector(Point2D p1, Point2D p2) {
        double midX = (p1.getX() + p2.getX()) / 2.0;
        double midY = (p1.getY() + p2.getY()) / 2.0;

        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();

        if (Math.abs(dx) < EPSILON && Math.abs(dy) < EPSILON) {
            return null;
        }

        // Iki nokta arasindaki dogruya dik dogru:
        // dx*(x - midX) + dy*(y - midY) = 0
        // dx*x + dy*y - (dx*midX + dy*midY) = 0
        double a = dx;
        double b = dy;
        double c = -(dx * midX + dy * midY);

        return new SeparatorLine(a, b, c, 0.0);
    }

    private boolean separatesAllPoints(SeparatorLine line, Dataset dataset) {
        for (LabeledPoint labeledPoint : dataset.getPoints()) {
            int predicted = line.predict(labeledPoint.getPoint());
            if (predicted != labeledPoint.getLabel()) {
                return false;
            }

            double raw = line.rawValue(labeledPoint.getPoint());
            if (Math.abs(raw) < EPSILON) {
                return false;
            }
        }
        return true;
    }

    private double calculateMinimumMargin(SeparatorLine line, Dataset dataset) {
        double minDistance = Double.POSITIVE_INFINITY;

        for (LabeledPoint labeledPoint : dataset.getPoints()) {
            double distance = line.distanceToPoint(labeledPoint.getPoint());
            if (distance < minDistance) {
                minDistance = distance;
            }
        }

        return minDistance;
    }

    public List<LabeledPoint> findSupportVectors(Dataset dataset, SeparatorLine line) {
        List<LabeledPoint> supportVectors = new ArrayList<>();
        double minDistance = calculateMinimumMargin(line, dataset);

        for (LabeledPoint labeledPoint : dataset.getPoints()) {
            double distance = line.distanceToPoint(labeledPoint.getPoint());
            if (Math.abs(distance - minDistance) < 1e-6) {
                supportVectors.add(labeledPoint);
            }
        }

        return supportVectors;
    }
}