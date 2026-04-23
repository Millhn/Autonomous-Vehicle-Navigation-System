package service;

import model.Dataset;
import model.LabeledPoint;
import model.Point2D;

public class DataGenerator {

    private DataGenerator() {
    }

    public static Dataset createSampleDataset() {
        Dataset dataset = new Dataset();

        // Sinif +1
        dataset.addPoint(new LabeledPoint(new Point2D(7, 8), 1));
        dataset.addPoint(new LabeledPoint(new Point2D(8, 7), 1));
        dataset.addPoint(new LabeledPoint(new Point2D(9, 9), 1));
        dataset.addPoint(new LabeledPoint(new Point2D(8, 10), 1));
        dataset.addPoint(new LabeledPoint(new Point2D(10, 8), 1));

        // Sinif -1
        dataset.addPoint(new LabeledPoint(new Point2D(1, 1), -1));
        dataset.addPoint(new LabeledPoint(new Point2D(2, 2), -1));
        dataset.addPoint(new LabeledPoint(new Point2D(3, 1), -1));
        dataset.addPoint(new LabeledPoint(new Point2D(2, 3), -1));
        dataset.addPoint(new LabeledPoint(new Point2D(1, 3), -1));

        return dataset;
    }
}