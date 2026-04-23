import model.Dataset;
import model.LabeledPoint;
import model.SeparatorLine;
import service.DataGenerator;
import solver.MaximumMarginSeparatorSolver;
import util.ConsolePrinter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Dataset dataset = DataGenerator.createSampleDataset();

        System.out.println("=== OTONOM ARAC GUVENLIK MODULU ===");
        System.out.println();

        ConsolePrinter.printDataset(dataset);

        MaximumMarginSeparatorSolver solver = new MaximumMarginSeparatorSolver();
        SeparatorLine bestLine = solver.findBestSeparator(dataset);

        if (bestLine == null) {
            System.out.println("Veri kumesi icin dogrusal ayristirici bulunamadi.");
            return;
        }

        System.out.println("\n=== EN IYI AYRISTIRICI BULUNDU ===");
        ConsolePrinter.printLine(bestLine);

        List<LabeledPoint> supportVectors = solver.findSupportVectors(dataset, bestLine);
        ConsolePrinter.printSupportVectors(supportVectors);

        System.out.println("\n=== TAHMIN SONUCLARI ===");
        for (LabeledPoint point : dataset.getPoints()) {
            int prediction = bestLine.predict(point.getPoint());
            System.out.printf(
                    "Nokta: %-20s Gercek Sinif: %-3d Tahmin: %-3d%n",
                    point.getPoint(), point.getLabel(), prediction
            );
        }

        System.out.println("\n=== ANALIZ ===");
        System.out.println("Bu dogru, iki sinifi ayiran gecerli dogrular arasindan");
        System.out.println("en kucuk nokta-dogru uzakligini maksimum yapan dogrudur.");
        System.out.println("Dolayisiyla en genis guvenlik koridorunu olusturur.");
    }
}